package com.atminterface.atminterface.exceptions;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 16-Sep-2025
 * Description: Custom exception thrown when a requested account is not found in the system
 */
public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
