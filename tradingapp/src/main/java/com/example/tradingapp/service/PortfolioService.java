package com.example.tradingapp.service;

import com.example.tradingapp.exception.AccountNotFoundException;
import com.example.tradingapp.model.Order;
import com.example.tradingapp.model.Portfolio;
import com.example.tradingapp.model.TradingAccount;
import com.example.tradingapp.repository.OrderRepository;
import com.example.tradingapp.repository.PortfolioRepository;
import com.example.tradingapp.repository.TradingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PortfolioService {
    @Autowired
    private TradingService tradingService;

    @Autowired
    private TradingAccountRepository accountRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PortfolioRepository portfolioRepository;

    /**
     * Ambil portfolio berdasarkan accountId
     */
    public Portfolio getPortfolioByAccountId(Long accountId) {
        // Pastikan akun ada
        TradingAccount account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        "Account not found with ID: " + accountId
                ));

        // Ambil semua order untuk akun ini
           List<Order> orders = orderRepository.findByAccount(account, PageRequest.of(0, Integer.MAX_VALUE))
                .getContent();

        // Hitung total saham yang dimiliki per simbol
        Map<String, Integer> holdings = new HashMap<>();

        for (Order order : orders) {
            String symbol = order.getSymbol();
            int qty = order.getQuantity();

            // BUY menambah kepemilikan, SELL mengurangi
            if ("BUY".equalsIgnoreCase(order.getType())) {
                holdings.put(symbol, holdings.getOrDefault(symbol, 0) + qty);
            } else if ("SELL".equalsIgnoreCase(order.getType())) {
                holdings.put(symbol, holdings.getOrDefault(symbol, 0) - qty);
            }
        }

        // Bersihkan saham yang sudah nol atau negatif
        holdings.entrySet().removeIf(e -> e.getValue() <= 0);

        // Buat Portfolio baru
        Portfolio portfolio = new Portfolio();
        portfolio.setAccount(account);
        portfolio.setHoldings(holdings);
        portfolio.setTotalValue(calculateTotalValue(holdings));

        // Simpan ke database (kalau ingin persist)
        portfolioRepository.save(portfolio);

        return portfolio;
    }

 private double calculateTotalValue(Map<String, Integer> holdings) {
    return holdings.entrySet().stream()
            .mapToDouble(e -> {
                try {
                    double price = tradingService.getCurrentPrice(e.getKey()).getPrice();
                    return e.getValue() * price;
                } catch (Exception ex) {
                    return 0.0; // kalau gagal ambil harga
                }
            })
            .sum();
}


    public List<Portfolio> getAllPortfolios() {
    return portfolioRepository.findAll();
    }

   public Portfolio getPortfolio(Long id) {
        return getPortfolioByAccountId(id);
    }

}
