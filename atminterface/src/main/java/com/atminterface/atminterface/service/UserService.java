package com.atminterface.atminterface.service;

import com.atminterface.atminterface.exceptions.AccountAlreadyExistsException;
import com.atminterface.atminterface.exceptions.AccountNotFoundException;
import com.atminterface.atminterface.model.AppUser;
import com.atminterface.atminterface.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public Optional<AppUser> findById(Long id) throws AccountNotFoundException, AccountAlreadyExistsException {
        {
            if (id == 0) {
                throw new AccountNotFoundException("Account not found");
            }
            if (userRepository.existsById(id)) {
                throw new AccountAlreadyExistsException("Account already exist");
            }
        }
        return userRepository.findById(id);
    }

    public AppUser findByEmail(String email) throws AccountNotFoundException {
        if (email == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return userRepository.findByEmail(email);
    }

    public AppUser findByFullName(String fullName) throws AccountNotFoundException {
        if (fullName == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return userRepository.findByFullName(fullName);
    }

    public AppUser findByUserid(String userid) throws AccountNotFoundException {
        if (userid == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return userRepository.findByUserid(userid);
    }

    public void deleteById(Long id) throws AccountNotFoundException {
        if (!userRepository.existsById(id)) {
            throw new AccountNotFoundException("Account doesnt exist");

        }
        userRepository.deleteById(id);
    }

    public List<AppUser> findAll() {

        List<AppUser> all = userRepository.findAll();
        if (all == null) {
            throw new RuntimeException("Data base has not initialised");

        }

        return all;
    }

    public <S extends AppUser> S save(S entity) {
        S save = userRepository.save(entity);
        if (save == null) {
            throw new RuntimeException("User information cannot be nul");

        }
        if (userRepository == null) {
            throw new RuntimeException("Informtion cant be blank");
        }
        if (entity.getFullName() == null || entity.getFullName().isBlank()) {
            throw new IllegalArgumentException("Full name cannot be blank");
        }
        if (entity.getEmail() == null || entity.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email cannot be blank");
        }
        if (entity.getPin() == null || entity.getPin().isBlank()) {
            throw new IllegalArgumentException("PIN cannot be blank");

        }
        return save;
    }
}