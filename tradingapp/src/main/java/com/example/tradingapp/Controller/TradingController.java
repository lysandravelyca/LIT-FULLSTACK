package com.example.tradingapp.Controller;

import com.example.tradingapp.dto.*;
import com.example.tradingapp.model.*;
import com.example.tradingapp.service.*;
import com.example.tradingapp.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/v1/trading")
@Validated
@CrossOrigin(origins = "http://localhost:4200")  // For Angular frontend
public class TradingController {
    
    @Autowired
    private TradingService tradingService;
    
    @Autowired
    private PortfolioService portfolioService;
    
    @Autowired
    private OrderService orderService;
    
    // GET - Retrieve all trading accounts
    @GetMapping("/accounts")
    public ResponseEntity<ApiResponse<List<TradingAccount>>> getAllAccounts() {
        List<TradingAccount> accounts = tradingService.getAllAccounts();
        return ResponseEntity.ok(
            ApiResponse.success("Accounts retrieved successfully", accounts)
        );
    }
    
    // GET - Retrieve specific account by ID
    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<ApiResponse<TradingAccount>> getAccount(
            @PathVariable Long accountId) {
        
        TradingAccount account = tradingService.getAccountById(accountId);
        return ResponseEntity.ok(
            ApiResponse.success("Account found", account)
        );
    }
    
    // POST - Create new trading account
    @PostMapping("/accounts")
    public ResponseEntity<ApiResponse<TradingAccount>> createAccount(
            @RequestBody @Valid CreateAccountRequest request) {
        
        TradingAccount account = tradingService.createAccount(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success("Account created successfully", account)
        );
    }
    
    // PUT - Update existing account
    @PutMapping("/accounts/{accountId}")
    public ResponseEntity<ApiResponse<TradingAccount>> updateAccount(
            @PathVariable Long accountId,
            @RequestBody @Valid UpdateAccountRequest request) {
        
        TradingAccount account = tradingService.updateAccount(accountId, request);
        return ResponseEntity.ok(
            ApiResponse.success("Account updated successfully", account)
        );
    }
    
    // DELETE - Close trading account
    @DeleteMapping("/accounts/{accountId}")
    public ResponseEntity<ApiResponse<Void>> closeAccount(
            @PathVariable Long accountId) {
        
        tradingService.closeAccount(accountId);
        return ResponseEntity.ok(
            ApiResponse.success("Account closed successfully", null)
        );
    }
    
    // GET - Retrieve portfolio for account
    @GetMapping("/accounts/{accountId}/portfolio")
    public ResponseEntity<ApiResponse<Portfolio>> getPortfolio(
            @PathVariable Long accountId) {
        
        Portfolio portfolio = portfolioService.getPortfolioByAccountId(accountId);
        return ResponseEntity.ok(
            ApiResponse.success("Portfolio retrieved", portfolio)
        );
    }
    
    // POST - Place buy order
    @PostMapping("/orders/buy")
    public ResponseEntity<ApiResponse<Order>> placeBuyOrder(
            @RequestBody @Valid BuyOrderRequest request) {
        
        Order order = orderService.placeBuyOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success("Buy order placed", order)
        );
    }
    
    // POST - Place sell order
    @PostMapping("/orders/sell")
    public ResponseEntity<ApiResponse<Order>> placeSellOrder(
            @RequestBody @Valid SellOrderRequest request) {
        
        Order order = orderService.placeSellOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success("Sell order placed", order)
        );
    }
    
    // GET - Retrieve order history
    @GetMapping("/accounts/{accountId}/orders")
    public ResponseEntity<ApiResponse<List<Order>>> getOrderHistory(
            @PathVariable Long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        List<Order> orders = orderService.getOrderHistory(accountId, page, size);
        return ResponseEntity.ok(
            ApiResponse.success("Order history retrieved", orders)
        );
    }
    
    // GET - Get current stock price
    @GetMapping("/stocks/{symbol}/price")
    public ResponseEntity<ApiResponse<StockPrice>> getStockPrice(
            @PathVariable String symbol) {
        
        StockPrice price = tradingService.getCurrentPrice(symbol);
        return ResponseEntity.ok(
            ApiResponse.success("Price retrieved", price)
        );
    }
    
    // GET - Search stocks
    @GetMapping("/stocks/search")
    public ResponseEntity<ApiResponse<List<Stock>>> searchStocks(
            @RequestParam String query) {
        
        List<Stock> stocks = tradingService.searchStocks(query);
        return ResponseEntity.ok(
            ApiResponse.success("Search results", stocks)
        );
    }
}
