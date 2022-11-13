package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huang.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息(User)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-10 16:13:27
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
