package com.ecommerce.admin.service.impl;

import com.ecommerce.admin.dao.CategoryDao;
import com.ecommerce.admin.dao.ProductDao;
import com.ecommerce.admin.dto.ProductDto;
import com.ecommerce.admin.entity.Category;
import com.ecommerce.admin.entity.Product;
import com.ecommerce.admin.fileHandling.File;
import com.ecommerce.admin.service.FileService;
import com.ecommerce.admin.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
    private final CategoryDao categoryDao;
    private final FileService fileService;

    @Value("${uploads.directory}")
    private String uploadsDirectory;

    public ProductDto addProduct(ProductDto productDto, MultipartFile imageFile) throws IOException {
        Product product = new Product();
        File file = this.fileService.create(imageFile, uploadsDirectory);
        product.setProductName(productDto.getProductName());
        product.setProductDescription(productDto.getProductDescription());
        product.setPrice(productDto.getPrice());
        product.setFile(file);

        Category category = categoryDao.findById(productDto.getCategoryId()).orElseThrow();

        product.setCategory(category);
        return productDao.save(product).getDto();
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productDao.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
    public List<ProductDto> getAllProductsByName(String name) {
        List<Product> products = productDao.findByProductNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public boolean deleteProduct(Long id){
        Optional <Product> optionalProduct= productDao.findById(id);
        if (optionalProduct.isPresent()){
            productDao.deleteById(id);
            return true;
        }
        return false;
    }

}
