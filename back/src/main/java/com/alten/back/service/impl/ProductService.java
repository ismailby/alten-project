package com.alten.back.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.alten.back.constants.Constants;
import com.alten.back.dto.ProductDTO;
import com.alten.back.exception.ErrorResponse;
import com.alten.back.mapper.IProductMapper;
import com.alten.back.model.Product;
import com.alten.back.repository.ProductRepository;
import com.alten.back.service.IProductService;

@Service
public class ProductService implements IProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private IProductMapper mapper;

	public List<ProductDTO> findAll() {
		return mapper.mapListOfProductEntity(productRepository.findAll());
	}

	@Override
	public ProductDTO findById(Long id) throws ErrorResponse {
		return mapper.productToProductDTO(productRepository.findById(id).orElseThrow(
				() -> new ErrorResponse("Produit n'existe pas.", Constants.ERROR_404, HttpStatus.NOT_FOUND)));
	}

	public ProductDTO create(ProductDTO productDTO, Authentication auth) throws ErrorResponse {
		this.checkIsAdmin(auth);
		Product productEntity = mapper.productDTOToProduct(productDTO);
		productEntity.setCreatedAt(System.currentTimeMillis());
		productEntity.setUpdatedAt(System.currentTimeMillis());
		return mapper.productToProductDTO(productRepository.save(productEntity));
	}

	public ProductDTO update(Long id, ProductDTO updatedDTO, Authentication auth) throws ErrorResponse {
		this.checkIsAdmin(auth);
		Product updatedEntity = mapper.productDTOToProduct(updatedDTO);
		Optional<Product> product = productRepository.findById(id);
		updatedEntity.setId(id);
		updatedEntity.setCreatedAt(product.get().getCreatedAt());
		updatedEntity.setUpdatedAt(System.currentTimeMillis());
		return mapper.productToProductDTO(productRepository.save(updatedEntity));
	}

	public void delete(Long id, Authentication auth) throws ErrorResponse {
		productRepository.deleteById(id);
	}

	public void checkIsAdmin(Authentication auth) throws ErrorResponse {
		if (auth == null || !auth.getName().equalsIgnoreCase("admin@admin.com")) {
			throw new ErrorResponse("Accès refusé. Seul l'administrateur peut effectuer cette action.",
					Constants.ERROR_403, HttpStatus.FORBIDDEN);
		}
	}

}
