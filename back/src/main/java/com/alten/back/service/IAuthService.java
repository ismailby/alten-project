package com.alten.back.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.alten.back.dto.UserDTO;
import com.alten.back.exception.ErrorResponse;
import com.alten.back.model.User;

public interface IAuthService {
	UserDTO register(UserDTO userDTO) throws ErrorResponse;

	ResponseEntity<?> login(UserDTO userDTO) throws ErrorResponse;

	Optional<User> findByEmail(String email);

}
