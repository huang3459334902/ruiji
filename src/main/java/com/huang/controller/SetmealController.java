package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.R;
import com.huang.dto.SetmealDTO;
import com.huang.entity.Category;
import com.huang.entity.Dish;
import com.huang.entity.Setmeal;
import com.huang.entity.ShoppingCart;
import com.huang.service.CategoryService;
import com.huang.service.SetmealService;
import com.huang.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * 套餐(Setmeal)表控制层
 *
 * @author makejava
 * @since 2022-11-07 20:16:08
 */
@Slf4j
@RestController
@RequestMapping("setmeal")
public class SetmealController {
    /**
     * 服务对象
     */
    @Autowired
    private SetmealService setmealService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @CacheEvict(value = "setmealCache",allEntries = true) //删除 setmealCache 缓存的数据
    @PostMapping("")
    public R<String> saveSetmeal(@RequestBody SetmealDTO setmealDTO) {
        return setmealService.saveSetmeal(setmealDTO);
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name) {
        return setmealService.page(page,pageSize,name);
    }

    @CacheEvict(value = "setmealCache",allEntries = true) //删除 setmealCache 缓存的数据
    @DeleteMapping("")
    public R<String> deleteSetmealByIds(String ids) {
        return setmealService.deleteSetmealByIds(ids);
    }

    @Cacheable(value = "setmealCache",key = "'List<Setmeal>'+#setmeal.categoryId")  //查询缓存数据
    @GetMapping("/list")
    public R<List<Setmeal>> list(Setmeal setmeal) {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Objects.nonNull(setmeal.getCategoryId()),Setmeal::getCategoryId,setmeal.getCategoryId());
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus,1);
        return R.success(setmealService.list(setmealLambdaQueryWrapper));
    }


    @GetMapping("/dish/{id}")
    public R<Setmeal> dish(@PathVariable Long id) {
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Objects.nonNull(id),Setmeal::getId,id);
        return R.success(setmealService.getOne(setmealLambdaQueryWrapper));
    }





}

