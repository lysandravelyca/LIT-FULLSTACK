package com.example.tradingapp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private TradingAccount account;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String type; // BUY / SELL

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(nullable = false)
private String orderType; // MARKET / LIMIT / STOP



    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TradingAccount getAccount() { return account; }
    public void setAccount(TradingAccount account) { this.account = account; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }


public String getOrderType() { return orderType; }
public void setOrderType(String orderType) { this.orderType = orderType; }
}
