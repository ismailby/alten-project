package com.alten.back.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.back.dto.UserDTO;
import com.alten.back.exception.ErrorResponse;
import com.alten.back.service.impl.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j

@Tag(name = "Authentification Controller", description = "Controller for auth users")
public class AuthController {
    
    @Autowired
    private AuthService authService;

    
    @Operation(summary = "Register", description = "S'inscrire")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO) throws ErrorResponse {
       UserDTO createdUser = authService.register(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Operation(summary = "Login", description = "S'authentifier avec un email et mot de passe")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) throws ErrorResponse {
    	return authService.login(userDTO);
    }
}
