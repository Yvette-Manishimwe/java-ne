package com.ecommerce.admin.dto;


import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String name;

    private String description;
}