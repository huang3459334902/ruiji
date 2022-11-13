package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.ShoppingCart;

/**
 * 购物车(ShoppingCart)表服务接口
 *
 * @author makejava
 * @since 2022-11-11 21:53:44
 */
public interface ShoppingCartService extends IService<ShoppingCart> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);
    
    
}
