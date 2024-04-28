package com.mtwesley.dictator.controller;

import com.mtwesley.dictator.model.security.AuthenticationResponse;
import com.mtwesley.dictator.model.security.LoginRequest;
import com.mtwesley.dictator.model.security.RefreshRequest;
import com.mtwesley.dictator.model.security.RegisterRequest;
import com.mtwesley.dictator.security.SecurityUtils;
import com.mtwesley.dictator.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/register/check")
    public ResponseEntity<?> checkUsernameExists(@RequestParam String username) {
        boolean exists = authenticationService.usernameExists(username);
        return exists ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.ok(exists);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest refreshRequest) {
        try {
            String newToken = authenticationService.refresh(refreshRequest.getToken());
            return ResponseEntity.ok(new AuthenticationResponse(SecurityUtils.getCurrentUsername(), newToken));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }
    }
}

