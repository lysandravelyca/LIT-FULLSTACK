package com.example.tradingapp.repository;

import com.example.tradingapp.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Portfolio findByAccountId(Long accountId);
}
