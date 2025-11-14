package com.example.tradingapp.model;

public class StockPrice {

    private String symbol;
    private Double price;
    private String currency;

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }
    
}
