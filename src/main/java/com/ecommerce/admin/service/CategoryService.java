package com.ecommerce.admin.service;

import com.ecommerce.admin.dto.CategoryDto;
import com.ecommerce.admin.entity.Category;

public interface CategoryService {
    Category createCategory(CategoryDto categoryDto);

}
