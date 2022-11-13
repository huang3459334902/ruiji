package com.huang.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.dao.SetmealDishMapper;
import com.huang.entity.SetmealDish;
import com.huang.service.SetmealDishService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 套餐菜品关系(SetmealDish)表服务实现类
 *
 * @author makejava
 * @since 2022-11-07 20:10:25
 */
@Service("setmealDishService")
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish> implements SetmealDishService {


    @Resource
    private SetmealDishMapper setmealDishMapper;
    
    /**
     * 根据ids删除多条数据
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            setmealDishMapper.deleteBatchIds(idList);
        }
    }
    
    
}
