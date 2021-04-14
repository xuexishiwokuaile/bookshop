package com.example.bookshop.exception;

/**
 * @author Alu
 * @date 2020/4/11 14:09
 */
public class UserException extends Exception {
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }
}
