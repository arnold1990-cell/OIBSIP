package com.atminterface.atminterface.dto;
import com.atminterface.atminterface.model.TransactionType;


import java.math.BigDecimal;

import java.util.UUID;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 14-Sep-2025
 * Description: Data Transfer Object for Transaction entity, including transactions
 */
public record TransactionDto(
        UUID id,
        BigDecimal amount,
 BigDecimal balanceAfter,
TransactionType transactionType
) {
}
