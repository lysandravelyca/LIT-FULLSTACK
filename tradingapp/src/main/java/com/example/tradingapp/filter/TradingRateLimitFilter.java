package com.example.tradingapp.filter;

import org.springframework.stereotype.Component;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Rate limiting filter for trading APIs
@Component
public class TradingRateLimitFilter implements Filter {
    
    private static final int MAX_REQUESTS_PER_MINUTE = 60;
    private final Map<String, RequestCounter> requestCounts = new ConcurrentHashMap<>();
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, 
                        FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        String clientIP = httpRequest.getRemoteAddr();
        
        // Check rate limit
        RequestCounter counter = requestCounts.computeIfAbsent(
            clientIP, 
            k -> new RequestCounter()
        );
        
        if (counter.increment() > MAX_REQUESTS_PER_MINUTE) {
            httpResponse.setStatus(429);  // Too Many Requests
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                "{\"error\":\"Rate limit exceeded. Please try again later.\"}"
            );
            return;
        }
        
        chain.doFilter(request, response);
    }
    
    // Helper class to track requests per client
    private static class RequestCounter {
        private int count = 0;
        private long windowStart = System.currentTimeMillis();
        
        public synchronized int increment() {
            long now = System.currentTimeMillis();
            if (now - windowStart > 60000) {  // Reset every minute
                count = 0;
                windowStart = now;
            }
            return ++count;
        }
    }
}
