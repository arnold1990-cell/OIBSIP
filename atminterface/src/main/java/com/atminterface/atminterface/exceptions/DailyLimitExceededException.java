package com.atminterface.atminterface.exceptions;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 16-Sep-2025
 * Description: Custom exception thrown when a user exceeds the daily transaction limit
 */
public class DailyLimitExceededException extends Exception {
    public DailyLimitExceededException(String message) {
        super(message);
    }
}
