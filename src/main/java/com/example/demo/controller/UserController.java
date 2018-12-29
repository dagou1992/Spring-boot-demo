package com.example.demo.controller;

import com.example.demo.bean.User;
import com.example.demo.service.UserDao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao UserDao;

    @GetMapping("/")
    public List<User> get() {
        List<User> u = UserDao.getUser();
        System.out.println(u);
        return u;
    }

    @PostMapping("/create")
    public void create() {
        UserDao.createUser();
    }
}
