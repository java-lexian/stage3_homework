package cn.edu.seu.mp.user;

import cn.edu.seu.mp.tbl.entity.User;
import cn.edu.seu.mp.tbl.service.impl.UserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: xuejing
 * @CreateTime: 2020-01-19 21:00
 * @Description: 测试 Service CRUD
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserServiceCRUD {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("zhan");
        user.setAge(33);
        user.setAddress("安徽省池州");
        // INSERT INTO tbl_user ( username, age, address ) VALUES ( ?, ?, ? )
        userServiceImpl.save(user);
    }

    @Test
    public void testRemove() {
        // 定义条件构造器
        User user = new User();
        user.setUsername("zhan");
        user.setAge(33);
        user.setAddress("安徽省池州");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        // DELETE FROM tbl_user WHERE username=? AND age=? AND address=?
        userServiceImpl.remove(wrapper);
    }

    @Test
    public void testUpdate() {
        // 根据 ID 选择修改
        User user = new User();
        user.setId(28);
        user.setUsername("LiMing");
        user.setAge(66);
        user.setAddress("shanghai");
        // UPDATE tbl_user SET username=?, age=?, address=? WHERE id=?
        userServiceImpl.updateById(user);
    }

    @Test
    public void testGet() {
        // SELECT id,username,age,birthday,address FROM tbl_user WHERE id=?
        userServiceImpl.getById(28);
    }

    @Test
    public void testList() {
        // 查询所有
        //SELECT username,age,birthday,address FROM tbl_user
        List<User> list = userServiceImpl.list();
        for (User user : list) {
            System.out.println(user.toString());
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testPage() {
        Page<User> page = new Page<>();
        // 设置每页的大小
        page.setSize(3);
        // 设置当前页
        page.setCurrent(1);
        User user = new User();
        //==>  Preparing: SELECT id,username,age,birthday,address FROM tbl_user LIMIT ?,?
        //==> Parameters: 0(Long), 3(Long)
        IPage<User> iPage = userServiceImpl.page(page);
        System.out.println(iPage.getRecords());
    }

    @Test
    public void testCount() {
        // 查询总记录数
        // SELECT COUNT( 1 ) FROM tbl_user
        userServiceImpl.count();
        // 根据 Wrapper 条件，查询总记录数
        User user = new User();
        user.setUsername("zhan");
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);
        // SELECT COUNT( 1 ) FROM tbl_user WHERE username=?
        userServiceImpl.count(wrapper);
    }

    /**
     * 条件查询
     */
    @Test
    public void testWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //设置条件
        Map<String, Object> params = new HashMap<>();
        params.put("username", "LiMing");
        params.put("age", 66);
        wrapper.allEq(params);
        //SELECT id,username,age,birthday,address FROM tbl_user WHERE age = ? AND username = ?
        List<User> users = userServiceImpl.list(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * 模糊查询
     */
    @Test
    public void testWrapperLike() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("username", "zh");
        //SELECT id,username,age,birthday,address FROM tbl_user WHERE username LIKE ?
        //==> Parameters: %zh%(String)
        List<User> users = userServiceImpl.list(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }


}
