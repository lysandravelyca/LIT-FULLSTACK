package com.example.tradingapp.exception;

import com.example.tradingapp.dto.ApiResponse;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
public class TradingExceptionHandler {
    
    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationErrors(
            MethodArgumentNotValidException ex) {
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(ApiResponse.error("Validation failed: " + errors.toString()));
    }

    // Handle insufficient funds
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<ApiResponse<Void>> handleInsufficientFunds(
            InsufficientFundsException ex) {
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ApiResponse.error(ex.getMessage())
        );
    }
    
    // Handle account not found
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleAccountNotFound(
            AccountNotFoundException ex) {
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ApiResponse.error(ex.getMessage())
        );
    }
    
    // Handle market closed
    @ExceptionHandler(MarketClosedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMarketClosed(
            MarketClosedException ex) {
        
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
            ApiResponse.error(ex.getMessage())
        );
    }
    
    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            ApiResponse.error("An unexpected error occurred: " + ex.getMessage())
        );
    }
}

