package com.huang.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.CustomException;
import com.huang.common.R;
import com.huang.dao.CategoryMapper;
import com.huang.dao.DishMapper;
import com.huang.dao.SetmealMapper;
import com.huang.entity.Category;
import com.huang.entity.Dish;
import com.huang.entity.Setmeal;
import com.huang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 菜品及套餐分类(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-11-05 19:34:10
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 根据ids删除多条数据
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            categoryMapper.deleteBatchIds(idList);
        }
    }

    @Override
    public R<String> saveCategory(Category category) {
        categoryMapper.insert(category);
        return R.success("新增成功");
    }

    @Override
    public R<Page> pageCategory(int page, int pageSize) {
        Page<Category> categoryPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Category::getSort);
        categoryMapper.selectPage(categoryPage,wrapper);
        return R.success(categoryPage);
    }

    //根据id删除分类 要判断是否关联别的数据
    @Override
    public R<String> deleteCategory(Long id) {

        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        dishWrapper.eq(Dish::getCategoryId,id);
        if (dishMapper.selectCount(dishWrapper) > 0) {
            throw new CustomException("当前菜品不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealWrapper = new LambdaQueryWrapper<>();
        setmealWrapper.eq(Setmeal::getCategoryId,id);
        if (setmealMapper.selectCount(setmealWrapper) > 0) {
            throw new CustomException("当前菜品不能删除");
        }

        categoryMapper.deleteById(id);
        return R.success("删除成功");
    }

    @Override
    public R<String> updateByIdCategory(Category category) {
        categoryMapper.updateById(category);
        return R.success("修改成功");
    }

    @Override
    public R<List<Category>> list(Category category) {
        LambdaQueryWrapper<Category> categoryWrapper = new LambdaQueryWrapper();
        categoryWrapper.eq(Objects.nonNull(category.getType()),Category::getType,category.getType());
        categoryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        categoryMapper.selectList(categoryWrapper);
        return R.success(categoryMapper.selectList(categoryWrapper));
    }


}
