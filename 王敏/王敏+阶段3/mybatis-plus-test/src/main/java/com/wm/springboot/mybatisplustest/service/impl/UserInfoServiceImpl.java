package com.wm.springboot.mybatisplustest.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wm.springboot.mybatisplustest.entity.UserInfo;
import com.wm.springboot.mybatisplustest.mapper.UserInfoMapper;
import com.wm.springboot.mybatisplustest.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生信息表 服务实现类
 * </p>
 *
 * @author WM
 * @since 2020-02-13
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Override
    public IPage<UserInfo> selectUserInfoByGtFraction(IPage<UserInfo> page, Long fraction) {
        return this.baseMapper.selectUserInfoByGtFraction(page,fraction);
    }
}
