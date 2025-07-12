package com.alten.back.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alten.back.model.User;
import com.alten.back.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	 User user = userRepository.findByEmail(email)
    	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    	        
    	        return new org.springframework.security.core.userdetails.User(
    	            user.getEmail(),
    	            user.getPassword(),
    	            List.of() // on pourrait ajouter des rôles ici
    	        );
    }
}