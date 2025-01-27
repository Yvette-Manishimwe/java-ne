package com.ecommerce.admin.dao;

import com.ecommerce.admin.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

}
