package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.Setmeal;

/**
 * 套餐(Setmeal)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-05 20:29:42
 */
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {

}
