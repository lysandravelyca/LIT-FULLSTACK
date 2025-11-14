package com.example.tradingapp.filter;

import org.springframework.stereotype.Component;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.logging.Logger;

// Request logging filter
@Component
public class TradingRequestLoggingFilter implements Filter {
    
    private static final Logger logger = Logger.getLogger(
        TradingRequestLoggingFilter.class.getName()
    );
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        long startTime = System.currentTimeMillis();
        
        String method = httpRequest.getMethod();
        String uri = httpRequest.getRequestURI();
        String clientIP = httpRequest.getRemoteAddr();
        
        logger.info(String.format("=> %s %s from %s", method, uri, clientIP));
        
        // Continue with request
        chain.doFilter(request, response);
        
        // Log response
        long duration = System.currentTimeMillis() - startTime;
        int status = httpResponse.getStatus();
        
        logger.info(String.format("<= %s %s - Status: %d, Duration: %dms", 
            method, uri, status, duration));
    }
}
