package com.alten.back.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.alten.back.dto.CartItemDTO;
import com.alten.back.model.CartItem;




/**
 * Interface pour le mappage entre l'entite {@link CartItem} et le DTO {@link CartItemDTO}.
 */

@Mapper(componentModel = "spring")
public interface ICartItemMapper {
	
	 // Conversion de CartItem en CartItemDTO
	CartItemDTO cartItemToCartItemDTO(CartItem cartItem);
    
    // Conversion de CartItemDTO en CartItem
	CartItem cartItemDTOToCartItem(CartItemDTO cartItemDTO);
    
    List<CartItemDTO> mapListOfCartItemEntity(List<CartItem> cartItem);

}