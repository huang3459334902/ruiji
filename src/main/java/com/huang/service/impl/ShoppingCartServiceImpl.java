package com.huang.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dao.ShoppingCartMapper;
import com.huang.entity.ShoppingCart;
import com.huang.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 购物车(ShoppingCart)表服务实现类
 *
 * @author makejava
 * @since 2022-11-11 21:53:44
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {


    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    
    /**
     * 根据ids删除多条数据
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            shoppingCartMapper.deleteBatchIds(idList);
        }
    }
    
    
}
