package com.jd.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.entity.User;
import com.jd.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cww
 * @since 2020-02-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping("/userList")
    public List<User> queryUserList(){
        return userService.list();
    }

    /**
     * 增加用户
     * @return
     */
    @GetMapping("/add")
    public ResponseEntity<User> add(){
        User user=new User();
        user.setAge("10");
        user.setCircleId(1);
        user.setEmail("751841475@qq.com");
        user.setPassword("123456");
        user.setSex("女");
        user.setUsername("test");
        if (userService.save(user))
        {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @GetMapping("/deleteUser")
    public ResponseEntity<String> delete(@Param("id")Integer id){
         if (userService.removeById(id))
         {
             return ResponseEntity.ok("删除成功");
         }
        return ResponseEntity.badRequest().build();
    }

    /**
     * 修改用户
     * @param id
     * @return
     */
    @GetMapping("/update")
    public ResponseEntity<User> update(@Param("id")Integer id){
        User user=new User();
        user.setUserId(id);
        user.setUsername("test1");
        if (userService.updateById(user)){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * QueryWrapper的使用
     * @param username
     * @return
     */
    @GetMapping("/findByUsername")
    public List<User> findByUsername(@Param("username")String username){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return userService.list(queryWrapper);
    }

    /**
     * UpdateWrapper的使用
     * @param username
     * @return
     */
    @GetMapping("/updateByUsername")
    public ResponseEntity<User> updateByUsername(@Param("username")String username){
        //修改值
        User user = new User();
        user.setAge("50");

        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("username", username);
        if (userService.update(user,userUpdateWrapper)){
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * 实现数据分页查询
     * @return
     */
    @GetMapping("/listByPage")
    public IPage<User> listByPage(){
        Page<User> page = new Page<>(1, 1);
        return userService.page(page,null);
    }
}
