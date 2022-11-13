package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.OrderDetail;

/**
 * 订单明细表(OrderDetail)表服务接口
 *
 * @author makejava
 * @since 2022-11-12 15:08:50
 */
public interface OrderDetailService extends IService<OrderDetail> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);
    
    
}
