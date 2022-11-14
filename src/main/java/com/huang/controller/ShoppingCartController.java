package com.huang.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huang.common.BaseContest;
import com.huang.common.R;
import com.huang.entity.ShoppingCart;
import com.huang.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/**
 * 购物车(ShoppingCart)表控制层
 *
 * @author makejava
 * @since 2022-11-11 21:54:01
 */
@Slf4j
@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {

    public static final Long iddd = BaseContest.getCurrentId();

    /**
     * 服务对象
     */
    @Autowired
    private ShoppingCartService shoppingCartService;


    @CacheEvict(value = "ShoppingCache",allEntries = true)
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {

        LambdaQueryWrapper<ShoppingCart> shoppingCartWrapper = new LambdaQueryWrapper<>();
        shoppingCartWrapper
                .eq(Objects.nonNull(shoppingCart.getDishId()),ShoppingCart::getDishId,shoppingCart.getDishId())
                .or()
                .eq(Objects.nonNull(shoppingCart.getSetmealId()),ShoppingCart::getSetmealId,shoppingCart.getSetmealId())
                .eq(ShoppingCart::getUserId,shoppingCart.getUserId());

        ShoppingCart one = shoppingCartService.getOne(shoppingCartWrapper);
        if (Objects.nonNull(one)) {
            Integer number = one.getNumber();
            one.setNumber(number + 1);
            shoppingCartService.updateById(one);
        } else {
            shoppingCart.setNumber(1);
            shoppingCartService.save(shoppingCart);
            one = shoppingCart;
        }

        return R.success(one);
    }

    @Cacheable(value = "ShoppingCache",key = "'List<ShoppingCart>'+#root.target.getBaseContest()")
    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, BaseContest.getCurrentId());
        shoppingCartLambdaQueryWrapper.orderByAsc(ShoppingCart::getCreateTime);

        return R.success(shoppingCartService.list(shoppingCartLambdaQueryWrapper));
    }
    public Long getBaseContest() {
        return BaseContest.getCurrentId();
    }

    @CacheEvict(value = "ShoppingCache",key = "'List<ShoppingCart>'+#root.target.getBaseContest()")
    @DeleteMapping("/clean")
    public R<String> clean() {
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId,BaseContest.getCurrentId());
        shoppingCartService.remove(shoppingCartLambdaQueryWrapper);
        return R.success("清空成功");
    }

    @CacheEvict(value = "ShoppingCache",allEntries = true)
    @PostMapping("/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart) {
        LambdaQueryWrapper<ShoppingCart> shoppingCartWrapper = new LambdaQueryWrapper<>();
        shoppingCartWrapper
                .eq(Objects.nonNull(shoppingCart.getDishId()),ShoppingCart::getDishId,shoppingCart.getDishId())
                .or()
                .eq(Objects.nonNull(shoppingCart.getSetmealId()),ShoppingCart::getSetmealId,shoppingCart.getSetmealId());

        ShoppingCart one = shoppingCartService.getOne(shoppingCartWrapper);

        if (one.getNumber() > 1) {
            one.setNumber(one.getNumber()-1);
            shoppingCartService.updateById(one);
        } else {
            shoppingCartService.removeById(one.getSetmealId());
        }

        return R.success("成功");
    }






}

