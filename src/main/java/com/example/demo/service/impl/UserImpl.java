package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.service.UserDao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;

@Slf4j
@RestController
public class UserImpl implements UserDao {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<User> getUser() {
        Query query = new Query();
        return mongoOperations.find(query, User.class);
    }

    @Override
    public void createUser() {
        User u = new User();
        u.setUserName("dfdsfsd");
        u.setPassword("dsdsadas");
        mongoOperations.save(u);
    }
}
