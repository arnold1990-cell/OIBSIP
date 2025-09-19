package com.atminterface.atminterface.controller;

import com.atminterface.atminterface.exceptions.AccountNotFoundException;
import com.atminterface.atminterface.exceptions.DailyLimitExceededException;
import com.atminterface.atminterface.exceptions.InsufficientBalanceException;
import com.atminterface.atminterface.model.Account;
import com.atminterface.atminterface.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/{accountNumber}")
    public ResponseEntity<?> findByAccountNumber(@PathVariable String accountNumber) throws AccountNotFoundException {
        Optional<Account> account = Optional.ofNullable(accountService.findByAccountNumber(accountNumber));
        if (account.isPresent()) {
            return new ResponseEntity<>(account.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<?>diposit(@PathVariable String accountNumber, @RequestParam BigDecimal amount) throws AccountNotFoundException {
        Optional<Account>accountOptional= Optional.ofNullable(accountService.deposit(accountNumber, amount));
        if (accountOptional.isPresent()) {return  new ResponseEntity<>(accountOptional.get(),HttpStatus.OK);

        }else {return new ResponseEntity<>("Cant perform a deposit",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable String accountNumber, @RequestParam BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException, DailyLimitExceededException {
        Optional<Account> optionalAccount = Optional.ofNullable(accountService.withdraw(accountNumber, amount));
        if (optionalAccount.isPresent()) {
            return new ResponseEntity<>(optionalAccount.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Sorry,you cant perform a withdrawal", HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/transfer")
    public ResponseEntity<?>transfer(@RequestParam String fromAccountNumber,@RequestParam String toAccountNumber,@RequestParam BigDecimal amount) throws InsufficientBalanceException, AccountNotFoundException {
        Optional<Account>optional=accountService.transfer(fromAccountNumber, toAccountNumber, amount);
        if (optional.isPresent()) {
            return new ResponseEntity<>(optional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Transfer failed", HttpStatus.BAD_REQUEST);
        }
    }
}