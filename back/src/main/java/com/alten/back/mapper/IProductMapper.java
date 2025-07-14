package com.alten.back.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.alten.back.dto.ProductDTO;
import com.alten.back.model.Product;



/**
 * Interface pour le mappage entre l'entite {@link Product} et le DTO {@link ProductDTO}.
 */

@Mapper(componentModel = "spring")
public interface IProductMapper {
	
	 // Conversion de Product en ProductDTO
    ProductDTO productToProductDTO(Product product);
    
    // Conversion de ProductDTO en Product
    Product productDTOToProduct(ProductDTO productDTO);
    
    List<ProductDTO> mapListOfProductEntity(List<Product> product);

}