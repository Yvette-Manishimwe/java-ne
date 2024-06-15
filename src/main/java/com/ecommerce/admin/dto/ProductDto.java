package com.ecommerce.admin.dto;

import com.ecommerce.admin.fileHandling.File;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private Long price;

    private String fileUrl;
    private String productDescription;
    private Long categoryId;
    private String categoryName;

}
