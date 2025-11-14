package com.example.tradingapp.model;

import jakarta.persistence.*;
import java.util.Map;

@Entity
@Table(name = "portfolios")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)
    private TradingAccount account;

    // Menyimpan daftar saham & jumlah
    @ElementCollection
    @CollectionTable(name = "portfolio_holdings", joinColumns = @JoinColumn(name = "portfolio_id"))
    @MapKeyColumn(name = "symbol")
    @Column(name = "quantity")
    private Map<String, Integer> holdings;

    private Double totalValue;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TradingAccount getAccount() { return account; }
    public void setAccount(TradingAccount account) { this.account = account; }

    public Map<String, Integer> getHoldings() { return holdings; }
    public void setHoldings(Map<String, Integer> holdings) { this.holdings = holdings; }

    public Double getTotalValue() { return totalValue; }
    public void setTotalValue(Double totalValue) { this.totalValue = totalValue; }
}
