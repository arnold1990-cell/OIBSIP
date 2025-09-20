package com.atminterface.atminterface.controller;

import com.atminterface.atminterface.dto.LoginRequest;
import com.atminterface.atminterface.model.AppUser;
import com.atminterface.atminterface.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "login";
    }


    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest,
                        BindingResult bindingResult,
                        Model model) {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            AppUser user = authService.login(loginRequest);

            model.addAttribute("user", user);
            return "dashboard"; // create dashboard.html later
        } catch (RuntimeException e) {

            model.addAttribute("loginError", e.getMessage());
            return "login";
        }
    }
}
