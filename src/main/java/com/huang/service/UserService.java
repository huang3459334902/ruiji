package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.User;

/**
 * 用户信息(User)表服务接口
 *
 * @author makejava
 * @since 2022-11-10 16:13:27
 */
public interface UserService extends IService<User> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);
    
    
}
