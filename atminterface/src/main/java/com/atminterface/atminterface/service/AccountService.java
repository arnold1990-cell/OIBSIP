package com.atminterface.atminterface.service;
import com.atminterface.atminterface.exceptions.AccountNotFoundException;
import com.atminterface.atminterface.exceptions.DailyLimitExceededException;
import com.atminterface.atminterface.exceptions.InsufficientBalanceException;
import com.atminterface.atminterface.model.Account;
import com.atminterface.atminterface.model.Transaction;
import com.atminterface.atminterface.model.TransactionType;
import com.atminterface.atminterface.repository.AccountRepository;
import com.atminterface.atminterface.repository.TransactionRepository;
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
    private final AccountRepository accountRepository ;
    private final TransactionRepository transactionRepository;

    private final Map<String, BigDecimal> dailyWithdrawals = new HashMap<>();
    private final BigDecimal DAILY_LIMIT = new BigDecimal("4000");

    public Account findByAccountNumber(String accountNumber) throws AccountNotFoundException {
        if (accountNumber == null) {
            throw new AccountNotFoundException("Account doesn't exist " );
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
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEPOSIT);
        transactionRepository.save(transaction);
        return accountRepository.save(account);
    }
    public Account withdraw(String accountNumber, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException, DailyLimitExceededException {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        if (accountNumber == null) {
            throw new AccountNotFoundException("Account not found");
        }
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
        Account updatedAccount = accountRepository.save(account);
        dailyWithdrawals.put(accountNumber, newTotal);
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.WITHDRAWAL);
        transactionRepository.save(transaction);
        return updatedAccount;
    }

    public  void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException {

        Account fromAccount = findByAccountNumber(fromAccountNumber);
        Account toAccount = findByAccountNumber(toAccountNumber);

        if (fromAccount.getBalance().compareTo(amount) < 0)
            throw new InsufficientBalanceException("Not enough balance for transfer");

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        accountRepository.save(fromAccount);
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(toAccount);

        Transaction outTransaction = new Transaction();
        outTransaction.setAccountNumber(fromAccountNumber);
        outTransaction.setAmount(amount);
        outTransaction.setType(TransactionType.TRANSFER_OUT);
        transactionRepository.save(outTransaction);

        Transaction inTransaction = new Transaction();
        inTransaction.setAccountNumber(toAccountNumber);
        inTransaction.setAmount(amount);
        inTransaction.setType(TransactionType.TRANSFER_IN);
        transactionRepository.save(inTransaction);
    }
}





