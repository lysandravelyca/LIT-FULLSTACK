package com.example.tradingapp.dto;

import jakarta.validation.constraints.*;

public class SellOrderRequest {
    @NotNull
    private Long accountId;

    @NotBlank
    private String symbol;

    @NotNull @Min(1)
    private Integer quantity;

    @NotNull @Min(0)
    private Double price;

    // Getters and setters
    public Long getAccountId() { return accountId; }
    public void setAccountId(Long accountId) { this.accountId = accountId; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}
