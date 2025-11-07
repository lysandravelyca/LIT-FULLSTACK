/**
 * Order Queue - Thread Coordination with Wait/Notify
 * 
 * Challenge: Implement a producer-consumer pattern using wait/notify
 * 
 * Your task: Complete the addOrder and processOrder methods to use wait/notify
 * for thread coordination.
 * 
 * Concepts covered:
 * - wait() and notify() / notifyAll()
 * - Producer-Consumer pattern
 * - Thread coordination
 * - Synchronized blocks
 */

package com.kulkultech.threads;

import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {
    private final Queue<Order> orders = new LinkedList<>();
    private final int MAX_CAPACITY = 100;
    private final Object lock = new Object();

    public void addOrder(Order order) throws InterruptedException {
        synchronized (lock) {
            // Tunggu jika antrian penuh
            while (orders.size() >= MAX_CAPACITY) {
                System.out.println("Queue full. Producer waiting...");
                lock.wait();
            }

            // Tambahkan order baru
            orders.add(order);
            System.out.println("Order added: " + order);

            // Bangunkan thread lain (terutama consumer)
            lock.notifyAll();
        }
    }
    
    public Order processOrder() throws InterruptedException {
        synchronized (lock) {
            // Tunggu jika antrian kosong
            while (orders.isEmpty()) {
                System.out.println("Queue empty. Consumer waiting...");
                lock.wait();
            }

            // Ambil order pertama
            Order order = orders.poll();
            System.out.println("Processing order: " + order);

            // Bangunkan thread lain (terutama producer)
            lock.notifyAll();

            return order;
        }
    }
    
    public int getQueueSize() {
        synchronized (lock) {
            return orders.size();
        }
    }
}

class Order {
    private String symbol;
    private int quantity;
    private String type;
    
    public Order(String symbol, int quantity, String type) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.type = type;
    }
    
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public String getType() { return type; }
    
    @Override
    public String toString() {
        return "Order{symbol='" + symbol + "', quantity=" + quantity + ", type='" + type + "'}";
    }
}
