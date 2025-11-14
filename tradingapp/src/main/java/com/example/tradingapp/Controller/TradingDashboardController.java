package com.example.tradingapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.tradingapp.model.Portfolio;
import com.example.tradingapp.service.PortfolioService;
import org.springframework.ui.Model;
import java.util.List;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/trading/dashboard")
public class TradingDashboardController {

     @Autowired
      private PortfolioService portfolioService;
      
     // Returns HTML view
     @GetMapping
     public String showDashboard(Model model) {
         List<Portfolio> portfolios = portfolioService.getAllPortfolios();
         model.addAttribute("portfolios", portfolios);
         return "dashboard";  // Returns dashboard.html
     }
     
     @GetMapping("/portfolio/{id}")
     public String viewPortfolio(@PathVariable Long id, Model model) {
         Portfolio portfolio = portfolioService.getPortfolio(id);
         model.addAttribute("portfolio", portfolio);
         return "portfolio-detail";
     }
    
}
