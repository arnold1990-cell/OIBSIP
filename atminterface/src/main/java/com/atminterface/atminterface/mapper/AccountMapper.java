package com.atminterface.atminterface.mapper;

import com.atminterface.atminterface.dto.AccountDto;
import com.atminterface.atminterface.model.Account;

import java.util.ArrayList;
import java.util.List;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 15-Sep-2025
 * Description: Mapper class for converting between Account entity and AccountDto
 */
public class AccountMapper {

    public static AccountDto toDto(Account account) {
        if (account == null) return null;

        return new AccountDto(
                account.getId(),
                account.getAccountNumber(),
                account.getBalance(),
                TransactionMapper.toDtoList(account.getTransactions())
        );
    }

    public static Account toEntity(AccountDto accountDto) {
        if (accountDto == null) return null;

        Account account = new Account();
        account.setId(accountDto.id());
        account.setAccountNumber(accountDto.accountNumber());
        account.setBalance(accountDto.balance());
        account.setTransactions(TransactionMapper.toEntityList(accountDto.transactions()));
        return account;
    }

    public static List<AccountDto> toDtoList(List<Account> accounts) {
        List<AccountDto> dtos = new ArrayList<>();
        if (accounts != null) {
            for (Account a : accounts) dtos.add(toDto(a));
        }
        return dtos;
    }

    public static List<Account> toEntityList(List<AccountDto> accountDtos) {
        List<Account> list = new ArrayList<>();
        if (accountDtos != null) {
            for (AccountDto aDto : accountDtos) list.add(toEntity(aDto));
        }
        return list;
    }
}

