package com.example.tradingapp.filter;

import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

// Authentication filter
@Component
public class TradingAuthenticationFilter implements Filter {
    
    private static final Logger logger = Logger.getLogger(
        TradingAuthenticationFilter.class.getName()
    );
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String authToken = httpRequest.getHeader("Authorization");
        String requestURI = httpRequest.getRequestURI();
        
        // Skip authentication for public endpoints
        if (requestURI.startsWith("/api/public/")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Validate authentication token
        if (authToken == null || !isValidToken(authToken)) {
            logger.warning("Unauthorized access attempt to: " + requestURI);
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                "{\"error\":\"Authentication required\"}"
            );
            return;
        }
        
        // Token is valid, proceed with request
        logger.info("Authenticated request to: " + requestURI);
        chain.doFilter(request, response);
    }
    
    private boolean isValidToken(String token) {
        // Simplified validation - in production, verify JWT or session
        return token != null && token.startsWith("Bearer ");
    }
}
