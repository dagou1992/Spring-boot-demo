package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadDao {
    String getData(MultipartFile file);
}
