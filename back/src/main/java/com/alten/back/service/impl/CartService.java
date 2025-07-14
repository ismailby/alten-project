package com.alten.back.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alten.back.dto.CartItemDTO;
import com.alten.back.dto.ProductDTO;
import com.alten.back.dto.UserDTO;
import com.alten.back.exception.ErrorResponse;
import com.alten.back.mapper.ICartItemMapper;
import com.alten.back.mapper.IProductMapper;
import com.alten.back.mapper.IUserMapper;
import com.alten.back.model.CartItem;
import com.alten.back.model.Product;
import com.alten.back.model.User;
import com.alten.back.repository.CartItemRepository;
import com.alten.back.service.ICartService;
import com.alten.back.service.IProductService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartService implements ICartService {

	@Autowired
	private CartItemRepository cartRepository;

	@Autowired
	private IProductService productService;

	@Autowired
	private ICartItemMapper mapper;

	@Autowired
	private IUserMapper userMapper;

	@Autowired
	private IProductMapper productMapper;

	public List<CartItemDTO> getCart(User user) {
		return mapper.mapListOfCartItemEntity(cartRepository.findByUser(user));
	}

	public void addToCart(User user, Long productId, int quantity) throws ErrorResponse {
		ProductDTO productDTO = productService.findById(productId);
		Product product = productMapper.productDTOToProduct(productDTO);
		Optional<CartItem> item = Optional
				.ofNullable(cartRepository.findByUserAndProduct(user, product).orElse(new CartItem()));

		item.get().setUser(user);
		item.get().setProduct(product);
		item.get().setQuantity(item.get().getQuantity() + quantity);

		cartRepository.save(item.get());
	}

	public void removeFromCart(User user, Long productId) throws ErrorResponse {
		ProductDTO productDTO = productService.findById(productId);
		Product product = productMapper.productDTOToProduct(productDTO);
		cartRepository.findByUserAndProduct(user, product).ifPresent(cartRepository::delete);
	}

	public void clearCart(User user) {
		cartRepository.deleteByUser(user);
	}

}
