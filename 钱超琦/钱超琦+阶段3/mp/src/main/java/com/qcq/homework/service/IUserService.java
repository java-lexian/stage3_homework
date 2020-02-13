package com.qcq.homework.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qcq.homework.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Doraemon
 * @since 2020-02-13
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
