package com.ecommerce.admin.service;

import com.ecommerce.admin.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public ProductDto addProduct(ProductDto productDto, MultipartFile image) throws IOException;
    public List<ProductDto> getAllProducts();
    public List<ProductDto> getAllProductsByName(String name);
    public boolean deleteProduct(Long id);
}
