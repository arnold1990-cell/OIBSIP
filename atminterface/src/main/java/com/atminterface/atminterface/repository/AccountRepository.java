package com.atminterface.atminterface.repository;

import com.atminterface.atminterface.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 15-Sep-2025
 * Description: Repository interface for Account entity, providing database access methods
 */

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumber(String accountNumber);

}
