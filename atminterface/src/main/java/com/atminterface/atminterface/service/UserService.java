package com.atminterface.atminterface.service;

import com.atminterface.atminterface.exceptions.AccountAlreadyExistsException;
import com.atminterface.atminterface.exceptions.AccountNotFoundException;
import com.atminterface.atminterface.model.AppUser;
import com.atminterface.atminterface.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final AppUserRepository appUserRepository;


    public UserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;

    }

    public Optional<AppUser> findById(Long id) throws AccountNotFoundException, AccountAlreadyExistsException {
        {
            if (id == 0) {
                throw new AccountNotFoundException("Account not found");
            }
            if (appUserRepository.existsById(id)) {
                throw new AccountAlreadyExistsException("Account already exist");
            }
        }
        return appUserRepository.findById(id);
    }

    public AppUser findByEmail(String email) throws AccountNotFoundException {
        if (email == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return appUserRepository.findByEmail(email);
    }

    public AppUser findByFullName(String fullName) throws AccountNotFoundException {
        if (fullName == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return appUserRepository.findByFullName(fullName);
    }

    public AppUser findByUserid(String userid) throws AccountNotFoundException {
        if (userid == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return appUserRepository.findByUserid(userid);
    }

    public void deleteById(Long id) throws AccountNotFoundException {
        if (!appUserRepository.existsById(id)) {
            throw new AccountNotFoundException("Account doesnt exist");

        }
        appUserRepository.deleteById(id);
    }

    public List<AppUser> findAll() {

        List<AppUser> all = appUserRepository.findAll();
        if (all == null) {
            throw new RuntimeException("Data base has not initialised");

        }

        return all;
    }

    public <S extends AppUser> S save(S entity) {
        S save = appUserRepository.save(entity);
        if (save == null) {
            throw new RuntimeException("User information cannot be nul");

        }
        if (appUserRepository == null) {
            throw new RuntimeException("Informtion cant be blank");
        }
        if (entity.getFullName() == null || entity.getFullName().isBlank()) {
            throw new IllegalArgumentException("Full name cannot be blank");
        }
        if (entity.getEmail() == null || entity.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (entity.getPassword() == null || entity.getPassword().isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty cannot be blank");

        }
        return save;
    }
}