package com.atminterface.atminterface.repository;

import com.atminterface.atminterface.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 15-Sep-2025
 * Description: Repository interface for User entity, providing database access methods
 */
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    User findByFullName(String fullName);
    User findByUserId(String userid );
}
