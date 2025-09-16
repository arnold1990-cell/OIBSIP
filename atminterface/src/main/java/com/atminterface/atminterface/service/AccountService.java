package com.atminterface.atminterface.service;

import com.atminterface.atminterface.exceptions.AccountNotFoundException;
import com.atminterface.atminterface.exceptions.DailyLimitExceededException;
import com.atminterface.atminterface.exceptions.InsufficientBalanceException;
import com.atminterface.atminterface.model.Account;
import com.atminterface.atminterface.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 16-Sep-2025
 * Description: Service class handling account operations (deposit, withdraw, transfer)
 */
@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    private final Map<String, BigDecimal> dailyWithdrawals = new HashMap<>();
    private final BigDecimal DAILY_LIMIT = new BigDecimal("4000");
    public Account findByAccountNumber(String accountNumber) throws AccountNotFoundException {
        if (accountNumber == null) {
            throw new AccountNotFoundException("Account doesn't exist " + accountNumber);
        }
        Account byAccountNumber = accountRepository.findByAccountNumber(accountNumber);
        if (byAccountNumber == null) {
            throw new AccountNotFoundException("Account doesn't exist " + accountNumber);
        }
        return byAccountNumber;
    }

    public Account deposit(String accountNumber, BigDecimal amount) throws AccountNotFoundException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        if (accountNumber == null) {
            throw new AccountNotFoundException("Account not found");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber);
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    public Account withdraw(String accountNumber, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException, DailyLimitExceededException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient funds");

        }
        if (accountNumber == null) {
            throw new AccountNotFoundException("Account not found");        }
        BigDecimal withdrawnToday = dailyWithdrawals.getOrDefault(accountNumber, BigDecimal.ZERO);
        BigDecimal newTotal = withdrawnToday.add(amount);
        if (newTotal.compareTo(DAILY_LIMIT) > 0) {
            throw new DailyLimitExceededException("Daily withdrawal limit of " + DAILY_LIMIT + " exceeded");
        }

        Account account = accountRepository.findByAccountNumber(accountNumber);
        if (account.getBalance().compareTo(amount) <= 0) {
            throw new InsufficientBalanceException("Withdrawal must be greater than zero");
        }
            account.setBalance(account.getBalance().subtract(amount));
            return accountRepository.save(account);
        }


    }
