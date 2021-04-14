package com.example.bookshop.exception;

public class SelectException extends Exception {
    public SelectException(String message) {
        super(message);
    }

    public SelectException(String message, Throwable cause) {
        super(message, cause);
    }
}
