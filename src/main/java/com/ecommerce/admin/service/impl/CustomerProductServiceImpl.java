package com.ecommerce.admin.service.impl;

import com.ecommerce.admin.dao.ProductDao;
import com.ecommerce.admin.dto.ProductDto;
import com.ecommerce.admin.entity.Product;
import com.ecommerce.admin.service.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {
    private final ProductDao productDao;
    public List<ProductDto> getAllProducts() {
        List<Product> products = productDao.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
    public List<ProductDto> getAllProductsByName(String name) {
        List<Product> products = productDao.findByProductNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
}
