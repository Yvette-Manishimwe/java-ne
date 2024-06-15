package com.ecommerce.admin.service.impl;

import com.ecommerce.admin.dao.CategoryDao;
import com.ecommerce.admin.dto.CategoryDto;
import com.ecommerce.admin.entity.Category;
import com.ecommerce.admin.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDao categoryDao;

    public Category createCategory(CategoryDto categoryDto){
        Category category= new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        return categoryDao.save(category);
    }
}
