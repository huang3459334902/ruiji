package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.ShoppingCart;

/**
 * 购物车(ShoppingCart)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-11 21:53:32
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}
