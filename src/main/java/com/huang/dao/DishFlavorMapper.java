package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.DishFlavor;

/**
 * 菜品口味关系表(DishFlavor)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-06 15:13:21
 */
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {

}
