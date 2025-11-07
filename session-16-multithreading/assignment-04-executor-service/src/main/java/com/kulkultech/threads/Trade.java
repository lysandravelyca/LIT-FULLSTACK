package com.kulkultech.threads;

/**
 * Represents a trading order containing symbol, quantity, and price.
 */
public class Trade {
    private String symbol;
    private int quantity;
    private double price;

    public Trade(String symbol, int quantity, double price) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "symbol='" + symbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
