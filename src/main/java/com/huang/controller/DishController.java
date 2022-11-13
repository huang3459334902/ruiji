package com.huang.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.R;
import com.huang.dto.DishDTO;
import com.huang.entity.Dish;
import com.huang.service.DishService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 菜品管理(Dish)表控制层
 *
 * @author makejava
 * @since 2022-11-06 15:15:08
 */
@RestController
@RequestMapping("dish")
public class DishController {
    /**
     * 服务对象
     */
    @Autowired
    private DishService dishService;

    @PostMapping("")
    public R<String> saveDish(@RequestBody DishDTO dishDTO) {
        return dishService.saveWithFlavor(dishDTO);
    }

    @GetMapping("/page")
    public R<Page> pageDish(int page,int pageSize,String name) {
        return dishService.pageDish(page,pageSize,name);
    }

    @GetMapping("/{id}")
    public R<DishDTO> dishDTOById(@PathVariable Long id) {
        return dishService.dishDTOById(id);
    }

    @PutMapping("")
    public R<String> insertDish(@RequestBody DishDTO dishDTO) {
        return dishService.saveWithFlavor(dishDTO);
    }

    @PostMapping("/status/{status}")
    public R<String> statusUpdateBiId(@PathVariable int status, String ids) {
        return dishService.statusUpdateBiId(status,ids);
    }

    @DeleteMapping("")
    public R<String> deleteByIds(String ids) {
        return dishService.deleteByIds(ids);
    }

    @GetMapping("/list")
    public R<List<DishDTO>> listDishById(Dish dish) {
        return dishService.listDishById(dish);
    }


}

