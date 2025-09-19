package com.atminterface.atminterface.controller;

import com.atminterface.atminterface.exceptions.AccountAlreadyExistsException;
import com.atminterface.atminterface.exceptions.AccountNotFoundException;
import com.atminterface.atminterface.model.AppUser;
import com.atminterface.atminterface.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) throws AccountAlreadyExistsException, AccountNotFoundException {
        Optional<AppUser> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{fullName}")
    public ResponseEntity<?> findByFullName(@PathVariable String fullName) throws AccountNotFoundException {
        Optional<AppUser> savedUser = Optional.ofNullable(userService.findByFullName(fullName));
        if (savedUser.isPresent()) {
            return new ResponseEntity<>(userService.findByFullName(fullName), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findByUser_Id(@PathVariable String userId) throws AccountNotFoundException {
        Optional<AppUser> optionalUser = Optional.ofNullable(userService.findByUserid(userId));
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User id not found", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) throws  AccountNotFoundException {
        Optional<AppUser> user = Optional.ofNullable(userService.findByEmail(email));
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
        }
    }
@GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
    List<AppUser> appUsers = userService.findAll();
    return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) throws AccountNotFoundException {
        userService.deleteById(id);
        return new ResponseEntity<>("Deleted ", HttpStatus.OK);
    }
@PostMapping
    public ResponseEntity<?> createUser(@RequestBody AppUser appUser) {
    AppUser savedAppUser = userService.save(appUser);
    try {
    return new ResponseEntity<>(savedAppUser, HttpStatus.CREATED);

        } catch (RuntimeException e) {
            return new ResponseEntity<>("User cant found", HttpStatus.CONFLICT);
        }
    }
}