class TradingDashboard {
  constructor() {
    this.watchlist = loadWatchlist();
    this.init();
  }

  init() {
    this.renderWatchlist();
    this.attachEventListeners();
    this.startAutoRefresh();
  }

  attachEventListeners() {
    document
      .getElementById("add-stock-form")
      .addEventListener("submit", (e) => {
        e.preventDefault();
        const symbol = e.target.symbol.value.trim().toUpperCase();
        if (!symbol) return;
        this.addToWatchlist(symbol);
        e.target.reset();
      });
  }

  async addToWatchlist(symbol) {
    try {
      const exists = this.watchlist.find((s) => s.symbol === symbol);
      if (exists) return alert(`${symbol} is already in your watchlist.`);

      const priceData = await fetchStockPrice(symbol);
      this.watchlist.push(priceData);
      saveWatchlist(this.watchlist);
      this.renderWatchlist();
    } catch (error) {
      alert(`Failed to add ${symbol}: ${error.message}`);
    }
  }

  removeFromWatchlist(symbol) {
    this.watchlist = this.watchlist.filter((stock) => stock.symbol !== symbol);
    saveWatchlist(this.watchlist);
    this.renderWatchlist();
  }

  async refreshPrices() {
    for (let i = 0; i < this.watchlist.length; i++) {
      const symbol = this.watchlist[i].symbol;
      try {
        const updated = await fetchStockPrice(symbol);
        this.watchlist[i] = updated;
      } catch (e) {
        console.error(`Failed to refresh ${symbol}:`, e);
      }
    }
    saveWatchlist(this.watchlist);
    this.renderWatchlist();
  }

  renderWatchlist() {
    const container = document.getElementById("stock-container");
    if (!this.watchlist.length) {
      container.innerHTML = `<p style="text-align:center;">No stocks added yet. Add one above!</p>`;
      return;
    }
    container.innerHTML = this.watchlist
      .map(
        (stock) => `
            <div class="stock-card">
                <button class="remove-btn" data-symbol="${
                  stock.symbol
                }">✖</button>
                <h3>${stock.symbol}</h3>
                <p class="price">$${stock.price.toFixed(2)}</p>
                <p class="change ${
                  stock.change >= 0 ? "positive" : "negative"
                }">
                    ${stock.change >= 0 ? "+" : ""}${stock.change.toFixed(2)}
                </p>
            </div>
        `
      )
      .join("");

    // Attach remove button event listeners
    document.querySelectorAll(".remove-btn").forEach((btn) => {
      btn.addEventListener("click", (e) => {
        const symbol = e.target.getAttribute("data-symbol");
        this.removeFromWatchlist(symbol);
      });
    });
  }

  startAutoRefresh() {
    setInterval(() => this.refreshPrices(), 10000); // every 10s
  }
}

document.addEventListener("DOMContentLoaded", () => {
  new TradingDashboard();
});
