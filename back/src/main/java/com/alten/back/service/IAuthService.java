package com.alten.back.service;


import org.springframework.http.ResponseEntity;

import com.alten.back.dto.UserDTO;
import com.alten.back.exception.ErrorResponse;

public interface IAuthService {
	  UserDTO register(UserDTO userDTO) throws ErrorResponse;
	  ResponseEntity<?> login(UserDTO userDTO) throws ErrorResponse;
	  
}
