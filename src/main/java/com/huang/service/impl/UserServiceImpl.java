package com.huang.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dao.UserMapper;
import com.huang.entity.User;
import com.huang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户信息(User)表服务实现类
 *
 * @author makejava
 * @since 2022-11-10 16:13:27
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;
    
    /**
     * 根据ids删除多条数据
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            userMapper.deleteBatchIds(idList);
        }
    }
    
    
}
