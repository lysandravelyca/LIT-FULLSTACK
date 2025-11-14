package com.example.tradingapp.exception;

public class MarketClosedException extends RuntimeException {

        public MarketClosedException(String message) {
        super(message);
    }

}
