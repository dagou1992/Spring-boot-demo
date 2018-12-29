package com.example.demo.service.impl;

import com.example.demo.bean.Upload;
import com.example.demo.service.UploadDao;

import java.io.*;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class UploadImpl implements UploadDao {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public String getData(MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请重新上传";
        }
        final File excelFile;
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            excelFile = File.createTempFile(UUID.randomUUID().toString(), ".json");
            // MultipartFile to File
            file.transferTo(excelFile);

            BufferedReader br = new BufferedReader(new FileReader(excelFile));
            String tempString = br.readLine();
            JSONArray array = JSONArray.fromObject(tempString);
            for (int i = 0; i < array.size(); i++) {
                Object o = array.get(i);
                if (o instanceof JSONNull) {
                    continue;
                }
                JSONObject jsonObject2 = JSONObject.fromObject(o);
                String id = jsonObject2.getString("id");
                JSONObject prediction = jsonObject2.getJSONObject("prediction");
                if (prediction == null || id == null || prediction.isEmpty()) {
                    continue;
                }
                Upload t = new Upload();
                t.setId(id);
                t.setPrediction(prediction);
                System.out.println(t);
                mongoOperations.save(t);
            }
            br.close();

            //程序结束时，删除临时文件
            deleteFile(excelFile);

            return "上传成功";

        } catch (IOException e) {
            return "上传失败";
        }
    }

    /**
     * 删除
     *
     * @param files
     */
    public void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
