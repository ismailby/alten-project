package com.alten.back.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.back.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {}