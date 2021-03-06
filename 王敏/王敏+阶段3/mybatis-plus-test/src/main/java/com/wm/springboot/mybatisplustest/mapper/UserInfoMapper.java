package com.wm.springboot.mybatisplustest.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wm.springboot.mybatisplustest.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 学生信息表 Mapper 接口
 * </p>
 *
 * @author WM
 * @since 2020-02-13
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    /**
     * 查询大于该分数的学生
     * @Param  page  分页参数
     * @Param  fraction  分数
     * @Return IPage<UserInfo> 分页数据
     */
    IPage<UserInfo> selectUserInfoByGtFraction(IPage<UserInfo> page, Long fraction);
}

