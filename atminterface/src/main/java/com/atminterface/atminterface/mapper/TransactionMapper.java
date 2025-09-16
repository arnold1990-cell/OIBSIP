package com.atminterface.atminterface.mapper;

import com.atminterface.atminterface.dto.TransactionDto;
import com.atminterface.atminterface.model.Transaction;

import java.util.ArrayList;
import java.util.List;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 16-Sep-2025
 * Description: Mapper class for converting between Transaction entity and TransactionDto
 */
public class TransactionMapper {


        public static TransactionDto toDto(Transaction transaction) {
            if (transaction == null) return null;

            return new TransactionDto(
                    transaction.getId(),
                    transaction.getAmount(),
                    transaction.getBalanceAfter(),
                    transaction.getTransactionType()
            );
        }

        public static Transaction toEntity(TransactionDto transactionDto) {
            if (transactionDto == null) return null;

            Transaction transaction = new Transaction();
            transaction.setId(transactionDto.id());
            transaction.setAmount(transactionDto.amount());
            transaction.setBalanceAfter(transactionDto.balanceAfter());
            transaction.setTransactionType(transactionDto.transactionType());
            return transaction;
        }

        public static List<TransactionDto> toDtoList(List<Transaction> transactions) {
            List<TransactionDto> dtos = new ArrayList<>();
            if (transactions != null) {
                for (Transaction t : transactions) dtos.add(toDto(t));
            }
            return dtos;
        }

        public static List<Transaction> toEntityList(List<TransactionDto> transactionDtos) {
            List<Transaction> list = new ArrayList<>();
            if (transactionDtos != null) {
                for (TransactionDto tDto : transactionDtos) list.add(toEntity(tDto));
            }
            return list;
        }
    }


