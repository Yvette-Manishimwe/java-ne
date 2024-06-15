package com.ecommerce.admin.service.impl;


import com.ecommerce.admin.dao.FileRepository;
import com.ecommerce.admin.fileHandling.File;
import com.ecommerce.admin.fileHandling.FileStorageService;
import com.ecommerce.admin.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl  implements FileService {
    private final FileRepository fileRepository;
    private final FileStorageService fileStorageService;

    @Value("${uploads.extensions}")
    private String extensions;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository, FileStorageService fileStorageService) {
        this.fileRepository = fileRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<File> getAll() {
        return this.fileRepository.findAll();
    }

    @Override
    public Page<File> getAll(Pageable pageable) {
        return  this.fileRepository.findAll(pageable);
    }

    @Override
    public File getById(int id) {
        return this.fileRepository.findById(id).get();
    }

    public String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex < 0) {
            return null;
        }
        return fileName.substring(dotIndex + 1);
    }

    public String handleFileName(String fileName, UUID id) {

        String cleanFileName = fileName.replaceAll("[^A-Za-z0-9.()]", "");
        String extension = getFileExtension(cleanFileName);

        String base = "image-" + id;

        cleanFileName = base + "." + extension;

        return cleanFileName;
    }

    @Override
    public File create(MultipartFile document, String directory) {
        File file = new File();

        String fileName = handleFileName(Objects.requireNonNull(document.getOriginalFilename()), UUID.randomUUID());

        file.setName(fileName);
        file.setPath(fileStorageService.save(document, directory, fileName));
        file.setType(document.getContentType());

        return this.fileRepository.save(file);
    }

    @Override
    public File getByName(String fileName) {
        return this.fileRepository.findByName(fileName);
    }

}