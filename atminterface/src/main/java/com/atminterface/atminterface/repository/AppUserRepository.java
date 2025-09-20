package com.atminterface.atminterface.repository;

import com.atminterface.atminterface.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Project: ATM Interface
 * Author: Arnold Madamombe
 * Date: 15-Sep-2025
 * Description: Repository interface for User entity, providing database access methods
 */
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
     AppUser findByEmail(String email);
    AppUser findByFullName(String fullName);
    AppUser findByUserid(String userid );


}
