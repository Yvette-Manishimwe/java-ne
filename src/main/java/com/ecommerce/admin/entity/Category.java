package com.ecommerce.admin.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Lob
    private String description;

}
