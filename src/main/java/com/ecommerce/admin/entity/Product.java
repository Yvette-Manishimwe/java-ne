package com.ecommerce.admin.entity;

import com.ecommerce.admin.dto.ProductDto;
import com.ecommerce.admin.fileHandling.File;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String productName;
    private Long price;
    @Lob
    private String productDescription;

    @OneToOne
    private File file;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private  Category category;

    public ProductDto getDto(){
        ProductDto productDto= new ProductDto();
        productDto.setId(id);
        productDto.setProductName(productName);
        productDto.setPrice(price);
        productDto.setProductDescription(productDescription);
        productDto.setCategoryId(category.getId());
        productDto.setCategoryName(category.getName());
        productDto.setFileUrl(file.getUrl());
        return productDto;
    }



}
