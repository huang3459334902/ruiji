package com.huang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.R;
import com.huang.entity.Category;
import com.huang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 菜品及套餐分类(Category)表控制层
 *
 * @author makejava
 * @since 2022-11-05 19:34:45
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Autowired
    private CategoryService categoryService;

    @PostMapping("")
    public R<String> saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/page")
    public R<Page> pageCategory(int page,int pageSize) {
        return categoryService.pageCategory(page,pageSize);
    }

    @DeleteMapping("")
    public R<String> deleteCategory(Long ids) {
        return categoryService.deleteCategory(ids);
    }

    @PutMapping("")
    public R<String> updateByIdCategory(@RequestBody Category category) {
        return categoryService.updateByIdCategory(category);
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category) {

        return categoryService.list(category);
    }



}

