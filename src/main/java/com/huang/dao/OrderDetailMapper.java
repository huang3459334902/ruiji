package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.OrderDetail;

/**
 * 订单明细表(OrderDetail)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-12 15:08:46
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}
