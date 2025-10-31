// storage.js - Helper functions for local storage

function saveWatchlist(watchlist) {
  localStorage.setItem("watchlist", JSON.stringify(watchlist));
}

function loadWatchlist() {
  const saved = localStorage.getItem("watchlist");
  return saved ? JSON.parse(saved) : [];
}
