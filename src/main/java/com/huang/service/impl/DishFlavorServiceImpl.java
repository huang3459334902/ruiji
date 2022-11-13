package com.huang.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dao.DishFlavorMapper;
import com.huang.entity.DishFlavor;
import com.huang.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 菜品口味关系表(DishFlavor)表服务实现类
 *
 * @author makejava
 * @since 2022-11-06 15:13:52
 */
@Service("dishFlavorService")
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {


    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    
    /**
     * 根据ids删除多条数据
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            dishFlavorMapper.deleteBatchIds(idList);
        }
    }
    
    
}
