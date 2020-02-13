package com.qcq;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcq.homework.entity.User;
import com.qcq.homework.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class MpApplicationTests {

    @Autowired
    private UserServiceImpl userService ;

    @Test
    void contextLoads() {
    }


    @Test
    void testList(){

        List<User> list = userService.list();
        for (User user:list
        ) {
            System.out.println(user);
        }
        System.out.println("========List========");
    }

    @Test
    void testGet(){
        User user = userService.getById(1);
        System.out.println(user);
        System.out.println("========Get========");
    }

    @Test
    void testAdd(){
        User user =new User();
        user.setId(6);
        user.setName("a");
        user.setPwd("123");
        userService.save(user);
        System.out.println("=========Add===========");
    }

    @Test
    void testUpdate(){
        User user =new User();
        user.setId(6);
        user.setName("b");
        user.setPwd("123456");
        userService.updateById(user);
        System.out.println("=========Update===========");
    }

    @Test
    void testDelete(){
        userService.removeById(6);
        System.out.println("=========delete===========");
    }

    @Test
    void testPage(){
        Page<User> page = new Page<>(1,2);
        IPage<User> iPage =userService.page(page);
        System.out.println(iPage.getRecords());
        System.out.println("当前页数："+iPage.getCurrent());
        System.out.println("总页数："+iPage.getPages());
        System.out.println("每页记录数："+iPage.getSize());
        System.out.println("总记录数："+iPage.getTotal());

        System.out.println("=========Page===========");
    }

    @Test
    void testWrapper(){
         QueryWrapper<User> queryWrapper =new QueryWrapper<>();
         queryWrapper.like("name","王");
         List<User> list = userService.list(queryWrapper);
        for (User user:list
        ) {
            System.out.println(user);
        }
        System.out.println("=========Wrapper===========");
    }



}
