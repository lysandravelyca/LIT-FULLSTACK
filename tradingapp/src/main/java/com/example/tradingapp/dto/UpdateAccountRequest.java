package com.example.tradingapp.dto;

public class UpdateAccountRequest {

    private String accountName;
    private String accountType;
    private Double balance;

    // Getters and Setters
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }

    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }

    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
    
}
