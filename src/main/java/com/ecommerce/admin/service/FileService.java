package com.ecommerce.admin.service;



import com.ecommerce.admin.entity.enums.EfileStatus;
import com.ecommerce.admin.exceptions.InvalidFileException;
import com.ecommerce.admin.fileHandling.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.lang.Integer;

public interface FileService {

    public List<File> getAll();

    public Page<File> getAll(Pageable pageable);
    public File getById(int id);


    public File create(MultipartFile document, String directory);
    public File getByName(String fileName);
}


