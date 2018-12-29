package com.example.demo.bean;

import lombok.Data;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User implements Serializable{
    private String userName;
    private String password;
}
