package com.atminterface.atminterface.service;

import com.atminterface.atminterface.exceptions.AccountAlreadyExistsException;
import com.atminterface.atminterface.exceptions.AccountNotFoundException;
import com.atminterface.atminterface.mapper.UserMapper;
import com.atminterface.atminterface.model.User;
import com.atminterface.atminterface.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private  final UserRepository userRepository;
    private  final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    public Optional<User> findById(Long id) throws AccountNotFoundException, AccountAlreadyExistsException {
         {
             if (id==0) {throw new AccountNotFoundException("Account not found");
             }
             if (userRepository.existsById(id)) { throw new AccountAlreadyExistsException("Account already exist");
             }
        }
        return userRepository.findById(id);
    }
    public User findByEmail(String email) throws AccountNotFoundException {
        if (email == null) {throw new AccountNotFoundException("Account not found");
        }
        return userRepository.findByEmail(email);
    }
    public User findByFullName(String fullName) throws AccountNotFoundException {
        if (fullName == null) {throw new AccountNotFoundException("Account not found");
        }
        return userRepository.findByFullName(fullName);
    }
    public User findByUserId(String userid) throws AccountNotFoundException {
        if (userid == null) {throw new AccountNotFoundException("Account not found");
        }
        return userRepository.findByUserId(userid);
    }
    public void deleteById(Long id) throws AccountNotFoundException {
        if (!userRepository.existsById(id)) {throw  new AccountNotFoundException("Account doesnt exist");

        }
        userRepository.deleteById(id);
    }
}
