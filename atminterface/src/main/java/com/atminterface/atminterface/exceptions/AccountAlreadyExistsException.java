package com.atminterface.atminterface.exceptions;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 16-Sep-2025
 * Description: Custom exception thrown when attempting to create an account that already exists
 */
public class AccountAlreadyExistsException extends Exception {
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}
