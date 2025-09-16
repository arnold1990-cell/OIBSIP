package com.atminterface.atminterface.exceptions;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 16-Sep-2025
 * Description: Custom exception thrown when a user attempts to withdraw or transfer more than the available account balance
 */
public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
