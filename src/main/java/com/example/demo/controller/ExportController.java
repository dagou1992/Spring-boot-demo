package com.example.demo.controller;

import com.example.demo.bean.Export;
import com.example.demo.service.ExportDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    private ExportDao exportDao;

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<FileSystemResource> exportData(@RequestBody Export form) {
        return exportDao.exportData(form);
    }

    @PostMapping("/save")
    @ResponseBody
    public void saveData(@RequestBody Export form) {
        exportDao.saveData(form);
    }
}
