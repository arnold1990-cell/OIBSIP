package com.atminterface.atminterface.exceptions;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 16-Sep-2025
 * Description: Custom exception thrown when no transactions are found for a user or account
 */
public class NoTransactionException extends Exception {
    public NoTransactionException(String message) {
        super(message);
    }
}
