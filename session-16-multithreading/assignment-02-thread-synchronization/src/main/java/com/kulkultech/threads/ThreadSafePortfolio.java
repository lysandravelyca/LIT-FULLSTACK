/**
 * Thread-Safe Portfolio - Thread Synchronization
 * 
 * Challenge: Make this portfolio class thread-safe using synchronization
 * 
 * Your task: Add synchronization to prevent race conditions when multiple threads
 * access the portfolio simultaneously.
 * 
 * Concepts covered:
 * - synchronized methods
 * - synchronized blocks
 * - race conditions
 * - thread safety
 */

package com.kulkultech.threads;

import java.util.HashMap;
import java.util.Map;

public class ThreadSafePortfolio {
    // Shared resources (perlu dilindungi)
    private final Map<String, Integer> stockHoldings = new HashMap<>();
    private double totalValue = 0.0;

    // Gunakan satu lock object untuk semua operasi terkait data bersama
    private final Object lock = new Object();

    public void addStock(String symbol, int shares) {
        synchronized (lock) {
            Integer currentShares = stockHoldings.getOrDefault(symbol, 0);
            stockHoldings.put(symbol, currentShares + shares);
            System.out.println("Added " + shares + " shares of " + symbol);
        }
    }
    public void updatePortfolioValue(Map<String, Double> currentPrices) {
        synchronized (lock) {
            double newTotalValue = 0.0;
            for (Map.Entry<String, Integer> entry : stockHoldings.entrySet()) {
                String symbol = entry.getKey();
                int shares = entry.getValue();
                double price = currentPrices.getOrDefault(symbol, 0.0);
                newTotalValue += shares * price;
            }
            this.totalValue = newTotalValue;
            System.out.println("Portfolio value updated: $" + String.format("%.2f", totalValue));
        }
    }


    public double getTotalValue() {
        synchronized (lock) {
            return totalValue;
        }
    }
    public Map<String, Integer> getHoldings() {
        synchronized (lock) {
            return new HashMap<>(stockHoldings);
        }
    }

    public int getStockCount(String symbol) {
        synchronized (lock) {
            return stockHoldings.getOrDefault(symbol, 0);
        }
    }
}
