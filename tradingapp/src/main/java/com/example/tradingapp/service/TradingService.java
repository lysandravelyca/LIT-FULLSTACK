package com.example.tradingapp.service;

import com.example.tradingapp.model.Stock;
import com.example.tradingapp.model.StockPrice;
import com.example.tradingapp.model.TradingAccount;
import com.example.tradingapp.dto.CreateAccountRequest;
import com.example.tradingapp.dto.UpdateAccountRequest;
import com.example.tradingapp.repository.TradingAccountRepository;
import com.example.tradingapp.exception.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TradingService {
    
    @Autowired
    private TradingAccountRepository accountRepository;
    
    public List<TradingAccount> getAllAccounts() {
        return accountRepository.findAll();
    }
    public TradingAccount getAccountById(Long id) {
        return accountRepository.findById(id)
            .orElseThrow(() -> new AccountNotFoundException(
                "Account not found with ID: " + id
            ));
    }
    
    public TradingAccount createAccount(CreateAccountRequest request) {
        TradingAccount account = new TradingAccount();
        account.setAccountName(request.getAccountName());
        account.setAccountType(request.getAccountType());
        account.setBalance(request.getInitialDeposit());
        account.setStatus("ACTIVE");
        
        return accountRepository.save(account);
    }
    
    public void closeAccount(Long id) {
        TradingAccount account = getAccountById(id);
        account.setStatus("CLOSED");
        accountRepository.save(account);
    }
    
    public TradingAccount updateAccount(Long accountId, UpdateAccountRequest request) {
        TradingAccount account = getAccountById(accountId);

        if (request.getAccountName() != null && !request.getAccountName().isBlank()) {
            account.setAccountName(request.getAccountName());
        }
        if (request.getAccountType() != null && !request.getAccountType().isBlank()) {
            account.setAccountType(request.getAccountType());
        }
        if (request.getBalance() != null) {
            account.setBalance(request.getBalance());
        }

        // updatedAt otomatis di-update oleh @PreUpdate
        return accountRepository.save(account);
    }

    public StockPrice getCurrentPrice(String symbol) {
        // Dummy implementation, bisa diganti dengan real API call nanti
        StockPrice price = new StockPrice();
        price.setSymbol(symbol.toUpperCase());
        price.setPrice(Math.round((Math.random() * 1000) * 100.0) / 100.0); // dua angka di belakang koma
        price.setCurrency("USD");
        return price;
    }
    
    public List<Stock> searchStocks(String query) {
        // Dummy stock list — nanti bisa diambil dari database atau API
        return List.of(
                new Stock("AAPL", "Apple Inc."),
                new Stock("GOOGL", "Alphabet Inc."),
                new Stock("TSLA", "Tesla Inc."),
                new Stock("AMZN", "Amazon.com Inc.")
        ).stream()
         .filter(stock -> stock.getSymbol().toLowerCase().contains(query.toLowerCase())
                       || stock.getName().toLowerCase().contains(query.toLowerCase()))
         .toList();
    }


}
