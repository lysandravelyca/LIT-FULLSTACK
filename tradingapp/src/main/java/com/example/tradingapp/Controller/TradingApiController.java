package com.example.tradingapp.Controller;
import com.example.tradingapp.model.Portfolio;
import com.example.tradingapp.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trading")
public class TradingApiController {
    
    @Autowired
    private PortfolioService portfolioService;
    
    // Returns JSON data directly
    @GetMapping("/portfolios")
    public ResponseEntity<List<Portfolio>> getAllPortfolios() {
        List<Portfolio> portfolios = portfolioService.getAllPortfolios();
        return ResponseEntity.ok(portfolios);
    }
    
    @GetMapping("/portfolio/{id}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable Long id) {
        Portfolio portfolio = portfolioService.getPortfolio(id);
        return ResponseEntity.ok(portfolio);
    }
}
