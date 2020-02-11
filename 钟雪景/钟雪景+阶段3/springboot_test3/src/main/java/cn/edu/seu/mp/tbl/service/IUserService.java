package cn.edu.seu.mp.tbl.service;

import cn.edu.seu.mp.tbl.entity.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xuejing
 * @since 2020-01-19
 */
public interface IUserService extends IService<User> {

    /**
     * 分页
     * @param page
     * @param state
     * @return
     */
    IPage<User> selectUserPage(Page<User> page, Integer state);

}
