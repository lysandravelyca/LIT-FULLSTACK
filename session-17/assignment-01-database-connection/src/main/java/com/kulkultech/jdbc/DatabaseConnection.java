/**
 * Database Connection - JDBC Connection Management
 * 
 * Challenge: Create and manage database connections using JDBC
 * 
 * Your task: Implement methods to establish and test database connections
 * 
 * Concepts covered:
 * - DriverManager.getConnection()
 * - Connection properties
 * - Connection validation
 * - Try-with-resources for resource management
 */

package com.kulkultech.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String dbUrl;
    private String username;
    private String password;
    
    public DatabaseConnection(String dbUrl, String username, String password) {
        // Initialize connection properties
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws SQLException {
        // Return a connection using DriverManager
        return DriverManager.getConnection(dbUrl, username, password);
    }

    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            // Check connection validity with 5 second timeout
            return conn != null && conn.isValid(5);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public String getDatabaseProductName() throws SQLException {
        try (Connection conn = getConnection()) {
            // Get connection metadata and return product name
            return conn.getMetaData().getDatabaseProductName();
        }
    }
}
