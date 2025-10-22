// 1. Store multiple stocks with their data
const portfolio = [
    { symbol: "AAPL", shares: 10, buyPrice: 150, currentPrice: 170 },
    { symbol: "GOOGL", shares: 5, buyPrice: 2800, currentPrice: 2600 },
    { symbol: "TSLA", shares: 3, buyPrice: 700, currentPrice: 800 },
    { symbol: "AMZN", shares: 2, buyPrice: 3200, currentPrice: 3300 },
];

// 2. Calculate total portfolio value
function calculateTotalValue(portfolio) {
    return portfolio.reduce((total, stock) => total + stock.shares * stock.currentPrice, 0);
}

// 3. Calculate profit/loss for each stock
function calculateProfitLoss(stock) {
    const profitLoss = (stock.currentPrice - stock.buyPrice) * stock.shares;
    const percentChange = ((stock.currentPrice - stock.buyPrice) / stock.buyPrice) * 100;
    return { profitLoss, percentChange };
}

// 4. Calculate overall portfolio performance
function calculateOverallPerformance(portfolio) {
    const totalInvested = portfolio.reduce((sum, s) => sum + s.shares * s.buyPrice, 0);
    const totalValue = calculateTotalValue(portfolio);
    const totalProfitLoss = totalValue - totalInvested;
    const percentChange = (totalProfitLoss / totalInvested) * 100;
    return { totalInvested, totalValue, totalProfitLoss, percentChange };
}

//Fungsi untuk nge-print 1 saham
function printStockDetails(stock) {
    const { profitLoss, percentChange } = calculateProfitLoss(stock);
    console.log(
        `${stock.symbol} | Shares: ${stock.shares} | Buy: $${stock.buyPrice.toFixed(2)} | ` +
        `Now: $${stock.currentPrice.toFixed(2)} | P/L: $${profitLoss.toFixed(2)} ` +
        `(${percentChange.toFixed(2)}%)`
    );
}

// 5. Display formatted report
function displayReport(portfolio) {
    console.log("=== Trading Portfolio Report ===\n");

    portfolio.forEach(printStockDetails);

    console.log("\n--------------------------------");
    const overall = calculateOverallPerformance(portfolio);
    console.log(`Total Invested: $${overall.totalInvested.toFixed(2)}`);
    console.log(`Current Value : $${overall.totalValue.toFixed(2)}`);
    console.log(
        `Overall P/L   : $${overall.totalProfitLoss.toFixed(2)} (${overall.percentChange.toFixed(2)}%)`
    );
    console.log("================================\n");
}

displayReport(portfolio);
