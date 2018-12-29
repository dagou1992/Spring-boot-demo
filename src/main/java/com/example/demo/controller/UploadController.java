package com.example.demo.controller;

import com.example.demo.service.UploadDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadDao uploadDao;

    @PostMapping("/")
    @ResponseBody
    public String getData(@RequestParam("file") MultipartFile file) {
        String t = uploadDao.getData(file);
        return t;
    }
}
