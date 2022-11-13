package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.R;
import com.huang.entity.Orders;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 订单表(Orders)表服务接口
 *
 * @author makejava
 * @since 2022-11-12 14:23:19
 */
public interface OrdersService extends IService<Orders> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);

    R<String> submit(@RequestBody Orders orders);
    
    
}
