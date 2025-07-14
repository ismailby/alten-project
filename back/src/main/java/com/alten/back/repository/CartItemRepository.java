package com.alten.back.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alten.back.model.CartItem;
import com.alten.back.model.Product;
import com.alten.back.model.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	 List<CartItem> findByUser(User user);

	Optional<CartItem> findByUserAndProduct(User user, Product product);
	void deleteByUser(User user);
}