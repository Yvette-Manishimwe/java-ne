package com.ecommerce.admin.controller;

import com.ecommerce.admin.dto.CategoryDto;
import com.ecommerce.admin.entity.Category;
import com.ecommerce.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    private ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
        Category category= categoryService.createCategory(categoryDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

}
