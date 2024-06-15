package com.ecommerce.admin.dao;

import com.ecommerce.admin.entity.enums.EfileStatus;
import com.ecommerce.admin.fileHandling.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Integer> {
        Page<File> findAllByStatus(Pageable pageable, EfileStatus status);
        File findByName(String fileName);
}
