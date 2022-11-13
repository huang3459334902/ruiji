package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.R;
import com.huang.dto.SetmealDTO;
import com.huang.entity.Dish;
import com.huang.entity.Setmeal;
import com.huang.entity.SetmealDish;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 套餐(Setmeal)表服务接口
 *
 * @author makejava
 * @since 2022-11-05 20:29:59
 */
public interface SetmealService extends IService<Setmeal> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);


    R<String> saveSetmeal(@RequestBody SetmealDTO setmealDTO);

    R<Page> page(int page, int pageSize, String name);

    R<String> deleteSetmealByIds(String ids);
    
    
}
