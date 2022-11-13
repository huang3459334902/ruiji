package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.Category;

/**
 * 菜品及套餐分类(Category)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-05 19:34:10
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
