package com.example.demo.service;

import com.example.demo.bean.User;
import java.util.List;

public interface UserDao {
    List<User> getUser();
    void createUser();
}
