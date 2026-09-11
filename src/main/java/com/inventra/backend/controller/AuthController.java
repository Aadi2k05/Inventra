package com.inventra.backend.controller;

import com.inventra.backend.dto.LoginRequest;
import com.inventra.backend.dto.LoginResponse;
import com.inventra.backend.dto.RegisterRequest;
import com.inventra.backend.dto.UserResponse;
import com.inventra.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse register(
            @Valid @RequestBody RegisterRequest request
    ) {

        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {

        return authService.login(request);
    }

    @GetMapping("/me")
    public UserResponse currentUser(
            Authentication authentication
    ) {

        return authService.getCurrentUser(
                authentication.getName()
        );
    }

    @PostMapping("/logout")
    public String logout() {

        /*
         * JWT authentication is stateless.
         * The frontend removes the stored token.
         */
        return "Logged out successfully";
    }
}