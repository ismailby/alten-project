package com.alten.back.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.back.dto.CartItemDTO;
import com.alten.back.dto.CartRequest;
import com.alten.back.exception.ErrorResponse;
import com.alten.back.model.User;
import com.alten.back.service.IAuthService;
import com.alten.back.service.ICartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/cart")

@Tag(name = "CartItem Controller", description = "Controller for Cart Items")
public class CartItemController {

	@Autowired
	private ICartService cartService;

	@Autowired
	private IAuthService authService;

	@Operation(summary = "Get CardItem", description = "Get CardItem")
	@GetMapping
	public ResponseEntity<?> getCart(Authentication auth) {
		Optional<User> user = Optional.ofNullable(authService.findByEmail(auth.getName()).orElseThrow());
		return ResponseEntity.ok(cartService.getCart(user.get()));
	}

	
	@Operation(summary = "ADD CardItem", description = "Add CardItem")
	@PostMapping
	public ResponseEntity<?> addToCart(@RequestBody CartRequest cartItemrequest, Authentication auth)
			throws ErrorResponse {
		User user = authService.findByEmail(auth.getName()).orElseThrow();
		cartService.addToCart(user, cartItemrequest.getProductId(), cartItemrequest.getQuantity());
		return ResponseEntity.ok("Produit ajouté au panier");
	}

	
	@Operation(summary = "Delete product from CartItem", description = "Delete product from CartItem")
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> removeFromCart(@PathVariable Long productId, Authentication auth) throws ErrorResponse {
		User user = authService.findByEmail(auth.getName()).orElseThrow();
		cartService.removeFromCart(user, productId);
		return ResponseEntity.ok("Produit supprimé du panier");
	}

	@Operation(summary = "Clear CartItem", description = "Clear CartItem")
	@DeleteMapping
	public ResponseEntity<?> clearCart(Authentication auth) {
		User user = authService.findByEmail(auth.getName()).orElseThrow();
		cartService.clearCart(user);
		return ResponseEntity.ok("Panier vidé");
	}

}
