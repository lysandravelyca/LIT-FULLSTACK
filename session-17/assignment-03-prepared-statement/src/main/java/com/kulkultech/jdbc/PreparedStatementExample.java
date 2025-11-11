/**
 * PreparedStatement Example - SQL Injection Prevention
 * 
 * Challenge: Use PreparedStatement to prevent SQL injection attacks
 * 
 * Your task: Replace Statement with PreparedStatement for safe parameterized queries
 * 
 * Concepts covered:
 * - PreparedStatement vs Statement
 * - Parameterized queries
 * - SQL injection prevention
 * - Setting parameters with setInt, setString, setDouble
 */
package com.kulkultech.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementExample {
    private final String dbUrl;
    private final String username;
    private final String password;
    
    public PreparedStatementExample(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }
    
    public boolean addTrade(int portfolioId, String symbol, int quantity, double price, String tradeType) {
        String sql = "INSERT INTO trades (portfolio_id, stock_symbol, quantity, price, trade_type) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, portfolioId);
            ps.setString(2, symbol);
            ps.setInt(3, quantity);
            ps.setDouble(4, price);
            ps.setString(5, tradeType);
            
            int affected = ps.executeUpdate();
            return affected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Trade> findTradesBySymbol(String symbol) {
        String sql = "SELECT id, stock_symbol, quantity, price, trade_type FROM trades WHERE stock_symbol = ?";
        
        List<Trade> trades = new ArrayList<>();
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, symbol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String stockSymbol = rs.getString("stock_symbol");
                    int quantity = rs.getInt("quantity");
                    double price = rs.getDouble("price");
                    String tradeType = rs.getString("trade_type");
                    
                    trades.add(new Trade(id, stockSymbol, quantity, price, tradeType));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return trades;
    }

    public boolean updateTradePrice(int tradeId, double newPrice) {
        String sql = "UPDATE trades SET price = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setDouble(1, newPrice);
            ps.setInt(2, tradeId);
            
            int affected = ps.executeUpdate();
            return affected > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public boolean testSqlInjectionSafe(String userInput) {
        String sql = "SELECT id FROM trades WHERE stock_symbol = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, userInput);
            try (ResultSet rs = ps.executeQuery()) {
                // Return true if there is at least one matching row
                return rs.next();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, username, password);
    }
}

class Trade {
    private int id;
    private String symbol;
    private int quantity;
    private double price;
    private String tradeType;
    
    public Trade(int id, String symbol, int quantity, double price, String tradeType) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeType = tradeType;
    }
    
    public int getId() { return id; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getTradeType() { return tradeType; }
}
