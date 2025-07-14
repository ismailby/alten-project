package com.alten.back.service;

import java.util.List;

import org.springframework.security.core.Authentication;

import com.alten.back.dto.ProductDTO;
import com.alten.back.exception.ErrorResponse;

public interface IProductService {
	    List<ProductDTO> findAll();
	    ProductDTO findById(Long id) throws ErrorResponse;
	    ProductDTO create(ProductDTO productDTO,Authentication auth) throws ErrorResponse;
	    ProductDTO update(Long id, ProductDTO updatedDTO,Authentication auth) throws ErrorResponse;
	    void delete(Long id,Authentication auth) throws ErrorResponse;
}
