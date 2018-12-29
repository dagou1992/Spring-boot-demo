package com.example.demo.bean;

import lombok.Data;
import net.sf.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "upload")
public class Upload {
    private String id;
    private JSONObject prediction;
}
