package com.example.demo.service;

import com.example.demo.bean.Export;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;

public interface ExportDao {
    ResponseEntity<FileSystemResource> exportData(Export form);
    void saveData(Export form);
}
