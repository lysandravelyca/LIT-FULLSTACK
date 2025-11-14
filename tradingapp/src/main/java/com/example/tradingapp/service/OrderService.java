package com.example.tradingapp.service;

import com.example.tradingapp.dto.SellOrderRequest;
import com.example.tradingapp.dto.BuyOrderRequest;
import com.example.tradingapp.exception.AccountNotFoundException;
import com.example.tradingapp.model.*;
import com.example.tradingapp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

/**
 * OrderService - Handles buy/sell order logic and history retrieval
 */
@Service
public class OrderService {

    @Autowired
    private TradingAccountRepository accountRepository;

    @Autowired
    private OrderRepository orderRepository;

    
    // buy order
     public Order placeBuyOrder(BuyOrderRequest request) {

        TradingAccount account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with ID: " + request.getAccountId()
                ));

        if ("LIMIT".equalsIgnoreCase(request.getOrderType()) && request.getLimitPrice() == null) {
            throw new IllegalArgumentException("Limit price is required for LIMIT orders");
        }

        double price = request.getLimitPrice() != null ? request.getLimitPrice() : 0.0;

        double totalCost = price > 0 ? request.getQuantity() * price : 0;

        if (price > 0 && account.getBalance() < totalCost) {
            throw new IllegalArgumentException("Insufficient balance to place buy order");
        }

        if (price > 0) {
            account.setBalance(account.getBalance() - totalCost);
            accountRepository.save(account);
        }

        Order order = new Order();
        order.setAccount(account);
        order.setSymbol(request.getSymbol().toUpperCase());
        order.setQuantity(request.getQuantity());
        order.setPrice(price);
        order.setType("BUY");
        order.setOrderType(request.getOrderType().toUpperCase());
        order.setTimestamp(LocalDateTime.now());
        orderRepository.save(order);

        return order;
    }
    
    //sell order
    public Order placeSellOrder(SellOrderRequest request) {
        TradingAccount account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with ID: " + request.getAccountId()
                ));

        // Tambahkan saldo dari hasil penjualan
        double totalValue = request.getQuantity() * request.getPrice();
        account.setBalance(account.getBalance() + totalValue);
        accountRepository.save(account);

        // Simpan order
        Order order = new Order();
        order.setAccount(account);
        order.setSymbol(request.getSymbol().toUpperCase());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());
        order.setType("SELL");
        order.setTimestamp(LocalDateTime.now());
        orderRepository.save(order);

        return order;
    }

   
    // order hsitory
    public List<Order> getOrderHistory(Long accountId, int page, int size) {
        TradingAccount account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with ID: " + accountId
                ));

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "timestamp"));
        Page<Order> orderPage = orderRepository.findByAccount(account, pageable);
        return orderPage.getContent();
    }
}
