package com.alten.back.service;

import java.util.List;


import com.alten.back.dto.CartItemDTO;
import com.alten.back.dto.CartRequest;
import com.alten.back.dto.UserDTO;
import com.alten.back.exception.ErrorResponse;
import com.alten.back.model.User;

public interface ICartService {
	List<CartItemDTO> getCart(User user);
	void addToCart(User user, Long productId, int quantity) throws ErrorResponse;
	void removeFromCart(User user, Long productId) throws ErrorResponse;
	void clearCart(User user);
}
