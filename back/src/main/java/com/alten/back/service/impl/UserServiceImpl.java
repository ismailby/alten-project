package com.alten.back.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.back.model.User;
import com.alten.back.repository.UserRepository;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

  
    public User create(User user) {
        // Hashage du mot de passe si nécessaire
        return userRepository.save(user);
    }

   
    public List<User> findAll() {
        return userRepository.findAll();
    }

   
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

  
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}