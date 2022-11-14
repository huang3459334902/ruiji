package com.huang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.R;
import com.huang.entity.Orders;
import com.huang.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


/**
 * 订单表(Orders)表控制层
 *
 * @author makejava
 * @since 2022-11-12 14:24:55
 */
@RestController
@RequestMapping("order")
public class OrdersController {
    /**
     * 服务对象
     */
    @Autowired
    private OrdersService ordersService;


    @Cacheable(value = "OrdersCache",key = "'Page'+#page+#pageSize")
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        return ordersService.submit(orders);
    }

    @Cacheable(value = "OrdersCache",key = "'Page'+#page+#pageSize")
    @GetMapping("/userPage")
    public R<Page> userPage(int page,int pageSize) {
        Page<Orders> ordersPage = new Page<>(page,pageSize);
        ordersService.page(ordersPage);
        return R.success(ordersPage);
    }


}

