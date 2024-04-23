package com.mtwesley.dictator.controller;

import com.mtwesley.dictator.model.security.AuthenticationResponse;
import com.mtwesley.dictator.model.security.LoginRequest;
import com.mtwesley.dictator.model.security.RegisterRequest;
import com.mtwesley.dictator.security.SecurityUtils;
import com.mtwesley.dictator.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            String token = authenticationService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new AuthenticationResponse(request.getUsername(), token));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Incorrect username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            authenticationService.register(request.getUsername(), request.getPassword());
            String token = authenticationService.login(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(new AuthenticationResponse(request.getUsername(), token));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam("refreshToken") String refreshToken) {
        try {
            String newToken = authenticationService.refresh(refreshToken);
            return ResponseEntity.ok(new AuthenticationResponse(SecurityUtils.getCurrentUsername(), newToken));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }
    }
}

