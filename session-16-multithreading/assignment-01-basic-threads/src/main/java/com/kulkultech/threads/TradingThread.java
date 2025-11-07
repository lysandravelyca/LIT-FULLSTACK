/**
 * Trading Thread - Basic Thread Creation
 * 
 * Challenge: Create a thread that processes trading operations
 * 
 * Your task: Complete this class to extend Thread and implement the run() method
 * to make the tests pass.
 * 
 * Concepts covered:
 * - Extending Thread class
 * - Implementing run() method
 * - Thread lifecycle (start, run, termination)
 * - Thread.sleep() for simulation
 */
package com.kulkultech.threads;

public class TradingThread extends Thread {
    protected String stockSymbol;
    protected double price;
    protected boolean processed = false;
    
    public TradingThread(String stockSymbol, double price) {
        // Memanggil konstruktor induk Thread dengan nama custom
        super("TradingThread-" + stockSymbol);
        this.stockSymbol = stockSymbol;
        this.price = price;
    }

    protected TradingThread(String name) {
        super(name);
    }
    
    @Override
    public void run() {
        try {
            // 1. Log awal proses
            System.out.println("Processing trade for " + stockSymbol + " at $" + price);
            
            // 2. Simulasikan proses selama 100ms
            Thread.sleep(100);
            
            // 3. Tandai bahwa sudah diproses
            processed = true;
            
            // 4. Log akhir proses
            System.out.println("Trade completed for " + stockSymbol);
            
        } catch (InterruptedException e) {
            // Jika thread di-interrupt selama sleep
            System.out.println("Trade for " + stockSymbol + " was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
    
    public boolean isProcessed() {
        return processed;
    }
    
    public String getStockSymbol() {
        return stockSymbol;
    }
    
    public double getPrice() {
        return price;
    }
}
