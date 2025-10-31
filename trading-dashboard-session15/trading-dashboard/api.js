// api.js - Simulated API call for fetching stock prices

async function fetchStockPrice(symbol) {
  try {
    // Simulate API delay
    await new Promise((res) => setTimeout(res, 500));

    // Mocked random data for demo
    const randomPrice = (Math.random() * 200 + 50).toFixed(2);
    const randomChange = (Math.random() * 10 - 5).toFixed(2);

    return {
      symbol: symbol.toUpperCase(),
      price: parseFloat(randomPrice),
      change: parseFloat(randomChange),
    };
  } catch (error) {
    throw new Error("Network error fetching stock data");
  }
}
