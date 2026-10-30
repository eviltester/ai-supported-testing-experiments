> show me javascript code to run in the console on any page which converts entire page into a canvas - with all current text and images intact, and then runs conways life against that canvas

Here is a complete, self-contained snippet you can paste directly into the browser DevTools Console (`F12` or `Ctrl+Shift+I` / `Cmd+Option+I` -> **Console**) on any webpage.

### How it works:
1. **Dynamic Capture**: Loads `html2canvas` on the fly to render the entire page DOM (text, fonts, CSS styles, SVGs, and images) onto an HTML5 `<canvas>`.
2. **State & Color Extraction**: Downsamples the snapshot into a cellular automata grid. Pixels contrasting with the background (e.g., text, buttons, photos) become living cells, retaining their original colors.
3. **Conway's Game of Life Engine**: Runs standard B3/S23 rules at 60 FPS. When new cells are born, they inherit and blend the colors of their neighbor cells.
4. **Interactive Overlay**: Covers the viewport with the simulation. Press <kbd>Escape</kbd> or click the floating close button at any time to remove the overlay and return to the normal page.

---

### The Code

```javascript
(async function runPageConwayLife() {
  console.log('%c[Life] Initializing Conway’s Game of Life on page...', 'color: #3b82f6; font-weight: bold;');

  // 1. Helper to load external script dynamically (html2canvas)
  const loadScript = (src) => new Promise((resolve, reject) => {
    if (window.html2canvas) return resolve(window.html2canvas);
    const s = document.createElement('script');
    s.src = src;
    s.onload = () => resolve(window.html2canvas);
    s.onerror = () => reject(new Error('Failed to load html2canvas library.'));
    document.head.appendChild(s);
  });

  // Create loading banner
  const banner = document.createElement('div');
  banner.style.cssText = `
    position: fixed; top: 20px; left: 50%; transform: translateX(-50%);
    background: rgba(15, 23, 42, 0.95); color: #fff; padding: 12px 24px;
    border-radius: 8px; font-family: system-ui, -apple-system, sans-serif;
    font-size: 14px; z-index: 2147483647; box-shadow: 0 8px 30px rgba(0,0,0,0.4);
    border: 1px solid rgba(255,255,255,0.1); pointer-events: none;
  `;
  banner.textContent = '📸 Capturing page screenshot into canvas...';
  document.body.appendChild(banner);

  try {
    await loadScript('https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js');

    // 2. Capture the current viewport & page into a canvas
    const capturedCanvas = await html2canvas(document.body, {
      scale: 1,
      useCORS: true,
      logging: false,
      windowWidth: window.innerWidth,
      windowHeight: window.innerHeight,
      x: window.scrollX,
      y: window.scrollY,
      width: window.innerWidth,
      height: window.innerHeight,
    });

    banner.remove();

    // 3. Setup Simulation Parameters
    const CELL_SIZE = 4; // Pixel size per Conway cell (adjust for resolution vs speed)
    const viewW = window.innerWidth;
    const viewH = window.innerHeight;
    const cols = Math.floor(viewW / CELL_SIZE);
    const rows = Math.floor(viewH / CELL_SIZE);
    const totalCells = cols * rows;

    // Display overlay canvas
    const displayCanvas = document.createElement('canvas');
    displayCanvas.width = viewW;
    displayCanvas.height = viewH;
    displayCanvas.style.cssText = `
      position: fixed; top: 0; left: 0; width: 100vw; height: 100vh;
      z-index: 2147483646; background: #000; cursor: crosshair;
    `;
    document.body.appendChild(displayCanvas);

    // Floating UI controls
    const uiBox = document.createElement('div');
    uiBox.style.cssText = `
      position: fixed; top: 16px; right: 16px; z-index: 2147483647;
      background: rgba(15, 23, 42, 0.85); backdrop-filter: blur(8px);
      padding: 10px 16px; border-radius: 8px; color: #e2e8f0;
      font-family: system-ui, -apple-system, sans-serif; font-size: 13px;
      display: flex; gap: 12px; align-items: center; border: 1px solid rgba(255,255,255,0.1);
    `;
    uiBox.innerHTML = `
      <span>🧬 <b>Conway's Life</b> (${cols}x${rows})</span>
      <button id="conway-close-btn" style="
        background: #ef4444; border: none; color: white; padding: 4px 10px;
        border-radius: 4px; cursor: pointer; font-size: 12px; font-weight: 600;
      ">Exit (Esc)</button>
    `;
    document.body.appendChild(uiBox);

    const dCtx = displayCanvas.getContext('2d');
    const capCtx = capturedCanvas.getContext('2d', { willReadFrequently: true });
    const imgData = capCtx.getImageData(0, 0, capturedCanvas.width, capturedCanvas.height).data;

    // 4. Initialize Game of Life grids & color buffers
    let currentGrid = new Uint8Array(totalCells);
    let nextGrid = new Uint8Array(totalCells);

    // Store RGB for each cell: [R, G, B] flat in Uint8ClampedArray
    const colors = new Uint8ClampedArray(totalCells * 3);
    const nextColors = new Uint8ClampedArray(totalCells * 3);

    // Sample average background brightness to automatically classify alive cells
    let totalLuminance = 0;
    let sampleCount = 0;
    for (let i = 0; i < imgData.length; i += 16) {
      totalLuminance += 0.299 * imgData[i] + 0.587 * imgData[i + 1] + 0.114 * imgData[i + 2];
      sampleCount++;
    }
    const avgLuminance = totalLuminance / sampleCount;
    const isDarkTheme = avgLuminance < 128;

    for (let y = 0; y < rows; y++) {
      for (let x = 0; x < cols; x++) {
        const idx = y * cols + x;
        const px = Math.min(capturedCanvas.width - 1, x * CELL_SIZE + Math.floor(CELL_SIZE / 2));
        const py = Math.min(capturedCanvas.height - 1, y * CELL_SIZE + Math.floor(CELL_SIZE / 2));
        const pIdx = (py * capturedCanvas.width + px) * 4;

        const r = imgData[pIdx];
        const g = imgData[pIdx + 1];
        const b = imgData[pIdx + 2];
        const lum = 0.299 * r + 0.587 * g + 0.114 * b;

        // Cells that contrast with the page background become "alive"
        const isAlive = isDarkTheme ? lum > avgLuminance + 20 : lum < avgLuminance - 20;

        currentGrid[idx] = isAlive ? 1 : 0;
        colors[idx * 3] = r;
        colors[idx * 3 + 1] = g;
        colors[idx * 3 + 2] = b;
      }
    }

    // Low-resolution offscreen canvas for blitting
    const simCanvas = document.createElement('canvas');
    simCanvas.width = cols;
    simCanvas.height = rows;
    const sCtx = simCanvas.getContext('2d');
    const simImgData = sCtx.createImageData(cols, rows);
    const simBuf32 = new Uint32Array(simImgData.data.buffer);

    // 5. Conway Game of Life loop
    let animId = null;

    function step() {
      for (let y = 0; y < rows; y++) {
        const yTop = (y === 0 ? rows - 1 : y - 1) * cols;
        const yCenter = y * cols;
        const yBottom = (y === rows - 1 ? 0 : y + 1) * cols;

        for (let x = 0; x < cols; x++) {
          const xLeft = x === 0 ? cols - 1 : x - 1;
          const xRight = x === cols - 1 ? 0 : x + 1;

          // 8 neighbor indices (toroidal boundary)
          const nIndices = [
            yTop + xLeft,   yTop + x,   yTop + xRight,
            yCenter + xLeft,            yCenter + xRight,
            yBottom + xLeft,yBottom + x,yBottom + xRight
          ];

          let aliveNeighbors = 0;
          let sumR = 0, sumG = 0, sumB = 0;

          for (let i = 0; i < 8; i++) {
            const nIdx = nIndices[i];
            if (currentGrid[nIdx] === 1) {
              aliveNeighbors++;
              sumR += colors[nIdx * 3];
              sumG += colors[nIdx * 3 + 1];
              sumB += colors[nIdx * 3 + 2];
            }
          }

          const selfIdx = yCenter + x;
          const isAlive = currentGrid[selfIdx] === 1;

          // Conway's Rules: B3 / S23
          if (isAlive && (aliveNeighbors === 2 || aliveNeighbors === 3)) {
            nextGrid[selfIdx] = 1;
            nextColors[selfIdx * 3] = colors[selfIdx * 3];
            nextColors[selfIdx * 3 + 1] = colors[selfIdx * 3 + 1];
            nextColors[selfIdx * 3 + 2] = colors[selfIdx * 3 + 2];
          } else if (!isAlive && aliveNeighbors === 3) {
            nextGrid[selfIdx] = 1;
            // Inherit average color from parent neighbor cells
            nextColors[selfIdx * 3] = sumR / 3;
            nextColors[selfIdx * 3 + 1] = sumG / 3;
            nextColors[selfIdx * 3 + 2] = sumB / 3;
          } else {
            nextGrid[selfIdx] = 0;
          }

          // Write directly to 32-bit pixel buffer for fast rendering
          if (nextGrid[selfIdx] === 1) {
            const r = nextColors[selfIdx * 3];
            const g = nextColors[selfIdx * 3 + 1];
            const b = nextColors[selfIdx * 3 + 2];
            simBuf32[selfIdx] = (255 << 24) | (b << 16) | (g << 8) | r;
          } else {
            // Dead cell background (fade or pure black)
            simBuf32[selfIdx] = 0xFF0B0F17; // Dark slate background
          }
        }
      }

      // Swap buffers
      currentGrid.set(nextGrid);
      colors.set(nextColors);

      // Render to main display canvas with nearest-neighbor scaling
      sCtx.putImageData(simImgData, 0, 0);
      dCtx.imageSmoothingEnabled = false;
      dCtx.drawImage(simCanvas, 0, 0, viewW, viewH);

      animId = requestAnimationFrame(step);
    }

    // Start simulation
    animId = requestAnimationFrame(step);

    // 6. Cleanup handler
    function cleanup() {
      if (animId) cancelAnimationFrame(animId);
      displayCanvas.remove();
      uiBox.remove();
      window.removeEventListener('keydown', onKey);
      console.log('%c[Life] Simulation terminated and cleaned up.', 'color: #10b981;');
    }

    function onKey(e) {
      if (e.key === 'Escape') cleanup();
    }

    window.addEventListener('keydown', onKey);
    document.getElementById('conway-close-btn').addEventListener('click', cleanup);

  } catch (err) {
    banner.remove();
    console.error('[Life] Error running Conway’s Life:', err);
    alert('Failed to initialize: ' + err.message);
  }
})();
```

---

### Customization Options

You can tweak the constants near the top of the script:
- `CELL_SIZE`: Set to `2` for ultra-fine high-density detail, or `6`–`8` for large retro arcade-style blocks.
- **Color blending**: Dead cells fade to `0xFF0B0F17` (dark slate). You can change it to transparent or another color if you want the underlying background visible.
- **Exit**: Click the red **Exit (Esc)** button in the top right or tap <kbd>Esc</kbd>.


Took about a minute to generate