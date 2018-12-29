package com.example.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.Export;
import com.example.demo.service.ExportDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Date;
import java.util.List;


@RestController
public class ExportImpl implements ExportDao {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public ResponseEntity<FileSystemResource> exportData(Export form) {
        Query query = new Query();
        String taskName = form.getTaskName();
        String truck = form.getTruck();
        String camera = form.getCamera();
        Long dateTime = form.getDateTime();
        int score = form.getScore();
        query.addCriteria(Criteria.where("taskName").is(taskName));
        query.addCriteria(Criteria.where("truck").is(truck));
        query.addCriteria(Criteria.where("camera").is(camera));
        query.addCriteria(Criteria.where("dateTime").is(dateTime));
        query.addCriteria(Criteria.where("score").is(score));
        List<Export> data = mongoOperations.find(query, Export.class);
        File file = new File("data.json");
        try {
            OutputStream os = new FileOutputStream(file);
            JSON.writeJSONString(os, data);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return export(file);
    }

    @Override
    public void saveData(Export form) {
        mongoOperations.save(form);
    }

    public ResponseEntity<FileSystemResource> export(File file) {
        if (file == null) {
            return null;
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", "data.json"));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/json"))
                .body(new FileSystemResource(file));
    }
}
