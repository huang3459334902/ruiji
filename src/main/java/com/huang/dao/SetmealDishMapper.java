package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.SetmealDish;

/**
 * 套餐菜品关系(SetmealDish)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-07 20:10:20
 */
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {

}
