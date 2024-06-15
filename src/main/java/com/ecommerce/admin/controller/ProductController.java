package com.ecommerce.admin.controller;

import com.ecommerce.admin.dto.ProductDto;
import com.ecommerce.admin.fileHandling.File;
import com.ecommerce.admin.fileHandling.FileStorageService;
import com.ecommerce.admin.service.FileService;
import com.ecommerce.admin.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    @Value("${uploads.directory}")
    private String directory;

    private final ProductService productService;
    private final FileStorageService fileStorageService;
    private final FileService fileService;

    @PostMapping("/newProduct")

    public ResponseEntity<ProductDto> addProduct(
            @ModelAttribute ProductDto productDto,
            @RequestParam("file") MultipartFile document
    ) throws IOException {
        ProductDto productDto1 = productService.addProduct(productDto, document);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name) {
        List<ProductDto> productDtos = productService.getAllProductsByName(name);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/load-file/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> loadProfileImage(@PathVariable String filename) {

        Resource file = this.fileStorageService.load(directory, filename);
        File fileObj = this.fileService.getByName(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, fileObj.getType())
                .body(file);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
        boolean deleted= productService.deleteProduct(productId);
        if (deleted){
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.notFound().build();
    }


}
