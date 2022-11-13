package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.SetmealDish;

/**
 * 套餐菜品关系(SetmealDish)表服务接口
 *
 * @author makejava
 * @since 2022-11-07 20:10:25
 */
public interface SetmealDishService extends IService<SetmealDish> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);
    
    
}
