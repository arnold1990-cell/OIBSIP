package com.atminterface.atminterface.service;

import com.atminterface.atminterface.dto.LoginRequest;
import com.atminterface.atminterface.model.AppUser;
import com.atminterface.atminterface.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AppUserRepository userRepository; // injected instance
    private final PasswordEncoder passwordEncoder;  // injected instance

    public AppUser login(LoginRequest request) {

        AppUser user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new RuntimeException("Invalid email or password");
        }


        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return user;
    }
}
