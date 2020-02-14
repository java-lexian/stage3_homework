package com.wm.springboot.mybatisplustest.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wm.springboot.mybatisplustest.entity.UserInfo;
import com.wm.springboot.mybatisplustest.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * <p>
 * 学生信息表 前端控制器
 * </p>
 *
 * @author WM
 * @since 2020-02-13
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 根据ID获取用户信息
     * @Param  userId  用户ID
     * @Return UserInfo 用户实体
     */
    @RequestMapping("/getInfo/{userId}")
    public UserInfo getInfo(@PathVariable String userId){
        UserInfo UserInfo = userInfoService.getById(userId);
        return UserInfo;
    }

    @RequestMapping("/getList")
    public List<UserInfo> getList(){
        List<UserInfo> UserInfoList = userInfoService.list();
        return UserInfoList;
    }

    /**
     * 分页查询全部数据
     * @Return IPage<UserInfo> 分页数据
     */
    @RequestMapping("/getInfoListPage")
    public IPage<UserInfo> getInfoListPage(){
        //需要在Config配置类中配置分页插件
        IPage<UserInfo> page = new Page<>();
        page.setCurrent(5); //当前页
        page.setSize(1);    //每页条数
        page = userInfoService.page(page);
        return page;
    }
    /**
     * 根据指定字段查询用户信息集合
     * @Return Collection<UserInfo> 用户实体集合
     */
    @RequestMapping("/getListMap")
    public Collection<UserInfo> getListMap(){
        Map<String,Object> map = new HashMap<>();
        //kay是字段名 value是字段值
        map.put("age",20);
        Collection<UserInfo> UserInfoList = userInfoService.listByMap(map);
        return UserInfoList;
    }
    /**
     * 新增用户信息
     */
    @RequestMapping("/saveInfo")
    public void saveInfo(){
        UserInfo UserInfo = new UserInfo();
        UserInfo.setName("小白");
        UserInfo.setSkill("JAVA");
        UserInfo.setAge(18);
        UserInfo.setFraction(59L);
        UserInfo.setEvaluate("该学生是一个在改BUG的码农");
        userInfoService.save(UserInfo);
    }
    /**
     * 批量新增用户信息
     */
    @RequestMapping("/saveInfoList")
    public void saveInfoList(){
        //创建对象
        UserInfo WM = new UserInfo();
        WM.setName("WM");
        WM.setSkill("睡觉");
        WM.setAge(18);
        WM.setFraction(60L);
        WM.setEvaluate("WM是一个爱睡觉,并且身材较矮的小胖子");
        UserInfo papyrus = new UserInfo();
        papyrus.setName("papyrus");
        papyrus.setSkill("JAVA");
        papyrus.setAge(18);
        papyrus.setFraction(58L);
        papyrus.setEvaluate("Papyrus是一个给人自信、有魅力的骷髅小瘦子");
        //批量保存
        List<UserInfo> list =new ArrayList<>();
        list.add(WM);
        list.add(papyrus);
        userInfoService.saveBatch(list);
    }
    /**
     * 更新用户信息
     */
    @RequestMapping("/updateInfo")
    public void updateInfo(){
        //根据实体中的ID去更新,其他字段如果值为null则不会更新该字段,参考yml配置文件
        UserInfo UserInfo = new UserInfo();
        UserInfo.setId(1L);
        UserInfo.setAge(19);
        userInfoService.updateById(UserInfo);
    }
    /**
     * 新增或者更新用户信息
     */
    @RequestMapping("/saveOrUpdateInfo")
    public void saveOrUpdate(){
        //传入的实体类UserInfo中ID为null就会新增(ID自增)
        //实体类ID值存在,如果数据库存在ID就会更新,如果不存在就会新增
        UserInfo UserInfo = new UserInfo();
        UserInfo.setId(1L);
        UserInfo.setAge(20);
        userInfoService.saveOrUpdate(UserInfo);
    }
    /**
     * 根据ID删除用户信息
     */
    @RequestMapping("/deleteInfo/{userId}")
    public void deleteInfo(@PathVariable String userId){
        userInfoService.removeById(userId);
    }
    /**
     * 根据ID批量删除用户信息
     */
    @RequestMapping("/deleteInfoList")
    public void deleteInfoList(){
        List<String> userIdlist = new ArrayList<>();
        userIdlist.add("12");
        userIdlist.add("13");
        userInfoService.removeByIds(userIdlist);
    }
    /**
     * 根据指定字段删除用户信息
     */
    @RequestMapping("/deleteInfoMap")
    public void deleteInfoMap(){
        //kay是字段名 value是字段值
        Map<String,Object> map = new HashMap<>();
        map.put("skill","删除");
        map.put("fraction",10L);
        userInfoService.removeByMap(map);
    }

    /**
     * Mybatis-plus自定义SQL
     * @Return IPage<UserInfoEntity> 分页数据
     */
    @RequestMapping("/getInfoListSQL")
    public IPage<UserInfo> getInfoListSQL(){
        //查询大于60分以上的学生,并且分页
        IPage<UserInfo> page = new Page<>();
        page.setCurrent(1);
        page.setSize(5);
        page = userInfoService.selectUserInfoByGtFraction(page,60L);
        return page;
    }

}

