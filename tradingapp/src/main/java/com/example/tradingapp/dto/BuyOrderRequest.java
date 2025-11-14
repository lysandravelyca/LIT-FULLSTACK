package com.example.tradingapp.dto;


// Buy order request

import jakarta.validation.constraints.*;

public class BuyOrderRequest {
    
    @NotNull(message = "Account ID is required")
    private Long accountId;
    
    @NotBlank(message = "Stock symbol is required")
    @Size(min = 1, max = 10, message = "Symbol must be 1-10 characters")
    private String symbol;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Minimum quantity is 1")
    private Integer quantity;
    
    @NotNull(message = "Order type is required")
    @Pattern(regexp = "MARKET|LIMIT|STOP", message = "Invalid order type")
    private String orderType;
    
    @DecimalMin(value = "0.01", message = "Limit price must be greater than 0")
    private Double limitPrice;  // Required for LIMIT orders
    
    // Getters and setters
    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
    
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    
    public String getOrderType() { return orderType; }
    public void setOrderType(String orderType) { this.orderType = orderType; }
    
    public Double getLimitPrice() { return limitPrice; }
    public void setLimitPrice(Double limitPrice) { this.limitPrice = limitPrice; }
}
