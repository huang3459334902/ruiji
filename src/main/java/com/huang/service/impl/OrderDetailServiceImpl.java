package com.huang.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dao.OrderDetailMapper;
import com.huang.entity.OrderDetail;
import com.huang.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 订单明细表(OrderDetail)表服务实现类
 *
 * @author makejava
 * @since 2022-11-12 15:08:51
 */
@Service("orderDetailService")
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {


    @Autowired
    private OrderDetailMapper orderDetailMapper;
    
    /**
     * 根据ids删除多条数据
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            orderDetailMapper.deleteBatchIds(idList);
        }
    }
    
    
}
