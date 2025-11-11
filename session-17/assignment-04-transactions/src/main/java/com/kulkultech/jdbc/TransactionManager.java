/**
 * Transaction Manager - JDBC Transaction Management
 * 
 * Challenge: Implement transaction management with commit and rollback
 * 
 * Your task: Use Connection transaction methods to ensure ACID properties
 * 
 * Concepts covered:
 * - setAutoCommit(false)
 * - commit()
 * - rollback()
 * - Savepoints
 * - Transaction isolation levels
 */

 package com.kulkultech.jdbc;

import java.sql.*;
import java.util.Map;

public class TransactionManager {
    private final String dbUrl;
    private final String username;
    private final String password;
    
    public TransactionManager(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }
    public boolean transferFunds(int fromAccountId, int toAccountId, double amount) {
        String withdrawSql = "UPDATE accounts SET balance = balance - ? WHERE account_id = ?";
        String depositSql = "UPDATE accounts SET balance = balance + ? WHERE account_id = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false); // start transaction

            try (PreparedStatement withdrawStmt = conn.prepareStatement(withdrawSql);
                 PreparedStatement depositStmt = conn.prepareStatement(depositSql)) {

                // Withdraw
                withdrawStmt.setDouble(1, amount);
                withdrawStmt.setInt(2, fromAccountId);
                withdrawStmt.executeUpdate();

                // Deposit
                depositStmt.setDouble(1, amount);
                depositStmt.setInt(2, toAccountId);
                depositStmt.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Transaction rolled back due to error: " + e.getMessage());
                return false;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMultiplePrices(Map<String, Double> priceUpdates) {
        String sql = "UPDATE positions SET current_price = ? WHERE stock_symbol = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            for (Map.Entry<String, Double> entry : priceUpdates.entrySet()) {
                stmt.setDouble(1, entry.getValue());
                stmt.setString(2, entry.getKey());
                stmt.addBatch();
            }

            stmt.executeBatch();
            conn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection conn = getConnection()) {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }

    public boolean processTradeWithSavepoint(int tradeId, double newPrice) {
        String updateTradeSql = "UPDATE trades SET price = ? WHERE trade_id = ?";
        String validateTradeSql = "SELECT price FROM trades WHERE trade_id = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement updateStmt = conn.prepareStatement(updateTradeSql);
                 PreparedStatement validateStmt = conn.prepareStatement(validateTradeSql)) {

                Savepoint beforeUpdate = conn.setSavepoint("BeforeUpdate");

                // Update trade
                updateStmt.setDouble(1, newPrice);
                updateStmt.setInt(2, tradeId);
                updateStmt.executeUpdate();

                // Validate
                validateStmt.setInt(1, tradeId);
                ResultSet rs = validateStmt.executeQuery();
                if (rs.next()) {
                    double price = rs.getDouble("price");
                    if (price <= 0) { // Invalid price
                        conn.rollback(beforeUpdate);
                        System.err.println("Validation failed — rolled back to savepoint.");
                        return false;
                    }
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                System.err.println("Rolled back transaction due to error: " + e.getMessage());
                return false;
            } finally {
                conn.setAutoCommit(true);
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
