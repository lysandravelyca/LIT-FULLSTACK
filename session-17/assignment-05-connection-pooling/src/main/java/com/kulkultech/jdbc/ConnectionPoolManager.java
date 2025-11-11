/**
 * Connection Pool Manager - Connection Pooling with HikariCP
 * 
 * Challenge: Implement connection pooling for efficient database connections
 * 
 * Your task: Use HikariCP to create and manage a connection pool
 * 
 * Concepts covered:
 * - HikariDataSource
 * - Connection pool configuration
 * - Pool size management
 * - Connection lifecycle
 */
/**
 * Connection Pool Manager - Connection Pooling with HikariCP
 * 
 * Challenge: Implement connection pooling for efficient database connections
 * 
 * Your task: Use HikariCP to create and manage a connection pool
 * 
 * Concepts covered:
 * - HikariDataSource
 * - Connection pool configuration
 * - Pool size management
 * - Connection lifecycle
 */

package com.kulkultech.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolManager {
    private HikariDataSource dataSource;

  
    public void createConnectionPool(String dbUrl, String username, String password, int maxPoolSize) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(username);
        config.setPassword(password);
        
      
        config.setMaximumPoolSize(maxPoolSize); 
        config.setMinimumIdle(Math.max(2, maxPoolSize / 4)); 
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000); 
        
        config.setPoolName("KulkulTechHikariPool");

        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Connection pool not initialized. Call createConnectionPool() first.");
        }
        return dataSource.getConnection();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

 
    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

    public String getPoolStats() {
        if (dataSource == null) {
            return "Pool not initialized";
        }
        var mxBean = dataSource.getHikariPoolMXBean();
        return String.format(
            "Pool stats: %d active, %d idle, %d total",
            mxBean.getActiveConnections(),
            mxBean.getIdleConnections(),
            mxBean.getTotalConnections()
        );
    }
}
