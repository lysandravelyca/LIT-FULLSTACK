/**
 * Trading Executor Service - ExecutorService Pattern
 * 
 * Challenge: Use ExecutorService to manage concurrent trading operations
 * 
 * Your task: Implement methods using ExecutorService to execute trades concurrently
 * 
 * Concepts covered:
 * - ExecutorService interface
 * - Thread pools (FixedThreadPool, CachedThreadPool)
 * - Future and CompletableFuture
 * - Shutdown and await termination
 */
package com.kulkultech.threads;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TradingExecutorService {
    private ExecutorService executor;
    
    public TradingExecutorService(int threadPoolSize) {
 
        executor = Executors.newFixedThreadPool(threadPoolSize);
    }
    

    public Future<Boolean> executeTradeAsync(Trade trade) {
        return executor.submit(() -> processTrade(trade));
    }
    
    public List<Future<Boolean>> executeTradesAsync(List<Trade> trades) {
        return trades.stream()
                .map(t -> executor.submit(() -> processTrade(t)))
                .collect(Collectors.toList());
    }
    

    private Boolean processTrade(Trade trade) {
        try {
            Thread.sleep(100); // Simulate trade processing delay
            System.out.println("Processed trade: " + trade.getSymbol() + " - " + trade.getQuantity() + " shares at $" + trade.getPrice());
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    public void shutdown() throws InterruptedException {
        executor.shutdown(); // Stop accepting new tasks
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("Forcing shutdown...");
            executor.shutdownNow(); // Force terminate remaining tasks
        } else {
            System.out.println("Executor service terminated gracefully.");
        }
    }
    
    public ExecutorService getExecutor() {
        return executor;
    }
}
