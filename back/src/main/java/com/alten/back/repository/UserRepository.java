package com.alten.back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.back.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	 Optional<User> findByEmail(String email);
	 User findByUsername(String username);
}