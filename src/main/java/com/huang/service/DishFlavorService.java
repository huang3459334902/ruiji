package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.DishFlavor;

/**
 * 菜品口味关系表(DishFlavor)表服务接口
 *
 * @author makejava
 * @since 2022-11-06 15:13:52
 */
public interface DishFlavorService extends IService<DishFlavor> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);
    
    
}
