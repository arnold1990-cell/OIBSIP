package com.atminterface.atminterface.service;

import com.atminterface.atminterface.exceptions.NoTransactionException;
import com.atminterface.atminterface.model.Transaction;
import com.atminterface.atminterface.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor// it`s a lombok annotation for constructors.
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public Optional<Transaction> findById(Long aLong) throws NoTransactionException {
        Optional<Transaction> byId = transactionRepository.findById(aLong);
        if (byId.isEmpty()) {
            throw new NoTransactionException("No existing transaction");

        }
        return byId;
    }

    public <S extends Transaction> S save(S entity) {
        S save = transactionRepository.save(entity);
        if (save.getId() == null) {
            throw new RuntimeException("No transaction");
        }
        System.out.println("Transaction save " + save);
        {

        }
        {

        }
        return save;
    }
}

