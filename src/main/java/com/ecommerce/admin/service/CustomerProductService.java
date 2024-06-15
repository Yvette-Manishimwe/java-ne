package com.ecommerce.admin.service;

import com.ecommerce.admin.dto.ProductDto;

import java.util.List;

public interface CustomerProductService {
    public List<ProductDto> getAllProducts();
    public List<ProductDto> getAllProductsByName(String name);
}
