package com.atminterface.atminterface.repository;

import com.atminterface.atminterface.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 15-Sep-2025
 * Description: Repository interface for Transaction entity, providing database access methods
 */
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
