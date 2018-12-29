package com.example.demo.bean;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "export")
public class Export implements Serializable {
    private String taskName;
    private String truck;
    private Long dateTime;
    private String camera;
    private int score;
}
