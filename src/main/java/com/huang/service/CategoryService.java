package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.R;
import com.huang.entity.Category;

import java.util.List;

/**
 * 菜品及套餐分类(Category)表服务接口
 *
 * @author makejava
 * @since 2022-11-05 19:34:10
 */
public interface CategoryService extends IService<Category> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);

    R<String> saveCategory(Category category);

    R<Page> pageCategory(int page,int pageSize);

    R<String> deleteCategory(Long id);

    R<String> updateByIdCategory(Category category);

    R<List<Category>> list(Category category);
    
    
}
