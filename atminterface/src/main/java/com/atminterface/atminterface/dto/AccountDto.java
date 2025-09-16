package com.atminterface.atminterface.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 14-Sep-2025
 * Description: Data Transfer Object for Account entity, including transactions
 */
public record AccountDto(
        UUID id,
        String accountNumber,
        BigDecimal balance,
 List<TransactionDto> transactions) {
}
