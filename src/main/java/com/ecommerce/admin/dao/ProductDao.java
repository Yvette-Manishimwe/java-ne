package com.ecommerce.admin.dao;

import com.ecommerce.admin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    List <Product> findByProductNameContaining(String title);
}
