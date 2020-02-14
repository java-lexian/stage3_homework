package com.jd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/userList")
    public List<Map<String, Object>> queryUserList(){

        String sql="select * from user";
        List<Map<String, Object>> userMaps=jdbcTemplate.queryForList(sql);

        return userMaps;
    }

    @GetMapping("/addUser")
    public void addUser(){

        String sql = "insert into user(username,password) values(?,?)";
        Object[] params =new Object[]{"test_jdbc","123456"};
        jdbcTemplate.update(sql,params);

    }

    @GetMapping("/updateUser")
    public void updateUser(){

        String sql = "update user set username = ? where user_id = ?";
        Object[] params =new Object[]{"test_jdb","9352"};
        jdbcTemplate.update(sql,params);

        jdbcTemplate.update(sql,params);

    }

    @GetMapping("/deleteUser")
    public void deleteUser(){

        String sql = "delete from user where username = ?";
        jdbcTemplate.update(sql,new Object[]{"test_jdb"});

    }
}
