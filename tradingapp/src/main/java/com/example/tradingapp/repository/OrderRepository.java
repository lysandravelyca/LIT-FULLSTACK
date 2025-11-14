package com.example.tradingapp.repository;

import com.example.tradingapp.model.Order;
import com.example.tradingapp.model.TradingAccount;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByAccount(TradingAccount account, Pageable pageable);
}
