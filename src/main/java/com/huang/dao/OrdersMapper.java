package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.Orders;

/**
 * 订单表(Orders)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-12 14:23:15
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}
