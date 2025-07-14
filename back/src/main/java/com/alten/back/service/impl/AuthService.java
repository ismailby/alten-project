package com.alten.back.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.alten.back.configuration.JwtUtils;
import com.alten.back.constants.Constants;
import com.alten.back.dto.UserDTO;
import com.alten.back.exception.ErrorResponse;
import com.alten.back.mapper.IUserMapper;
import com.alten.back.model.User;
import com.alten.back.repository.UserRepository;
import com.alten.back.service.IAuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService implements IAuthService {
	@Autowired
	private IUserMapper mapper;

	@Autowired
	private UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;
	private final JwtUtils jwtUtils;
	private final AuthenticationManager authenticationManager;

	public UserDTO register(UserDTO userDTO) throws ErrorResponse {
		User productEntity = mapper.userDTOToUser(userDTO);
		if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
			throw new ErrorResponse("Email is already in use", Constants.ERROR_409, HttpStatus.CONFLICT);
		}
		productEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		return mapper.userToUserDTO(userRepository.save(productEntity));
	}

	public ResponseEntity<?> login(UserDTO userDTO) throws ErrorResponse {

		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
			if (authentication.isAuthenticated()) {
				Map<String, Object> authData = new HashMap<>();
				authData.put("token", jwtUtils.generateToken(userDTO.getEmail()));
				authData.put("type", "Bearer");
				return ResponseEntity.ok(authData);
			}
			return null;
		} catch (AuthenticationException e) {
			log.error(e.getMessage());
			throw new ErrorResponse("Invalid username or password", Constants.ERROR_401, HttpStatus.UNAUTHORIZED);

		}

	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

}
