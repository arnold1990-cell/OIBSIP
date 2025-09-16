package com.atminterface.atminterface.dto;

import java.util.List;
import java.util.UUID;

/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 14-Sep-2025
 * Description: Data Transfer Object for User entity, including transactions
 */
public record UserDto(
        UUID id,
        String user_id,
        String fullName,
        String email,
        List<AccountDto> accounts)
{
}
