package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.Dish;

/**
 * 菜品管理(Dish)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-05 20:29:37
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}
