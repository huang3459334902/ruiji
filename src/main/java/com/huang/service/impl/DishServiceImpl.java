package com.huang.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.R;
import com.huang.dao.CategoryMapper;
import com.huang.dao.DishFlavorMapper;
import com.huang.dao.DishMapper;
import com.huang.dto.DishDTO;
import com.huang.entity.Category;
import com.huang.entity.Dish;
import com.huang.entity.DishFlavor;
import com.huang.service.DishFlavorService;
import com.huang.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 菜品管理(Dish)表服务实现类
 *
 * @author makejava
 * @since 2022-11-05 20:29:53
 */
@Service("dishService")
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {


    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @CacheEvict(value = "DishCache",allEntries = true)
    @Override
    public R<String> saveWithFlavor(DishDTO dishDTO) {
        if (Objects.isNull(dishDTO.getId())) {
            dishMapper.insert(dishDTO);
            //菜品id
            Long id = dishDTO.getId();
            //菜品口味
            List<DishFlavor> flavors = dishDTO.getFlavors();
            flavors = flavors.stream().map((item) -> {
                item.setDishId(id);
                return item;
            }).collect(Collectors.toList());
            dishFlavorService.saveBatch(flavors);
            return R.success("新增成功");
        }

        Dish dish = new Dish();
        LambdaQueryWrapper<DishFlavor> dishFlavorWrapper = new LambdaQueryWrapper<>();
        dishFlavorWrapper.in(DishFlavor::getDishId,dishDTO.getId());
        dishFlavorMapper.delete(dishFlavorWrapper);

        BeanUtils.copyProperties(dishDTO,dish);

        dishDTO.getFlavors().stream().map((item -> {
            item.setId(null);
            return item;
        })).collect(Collectors.toList());

        dishMapper.updateById(dish);
        dishFlavorService.saveBatch(dishDTO.getFlavors());
        return R.success("修改成功");
    }

    @Cacheable(value = "DishCache",key = "'Page'+#page+#pageSize+#name")
    @Override
    public R<Page> pageDish(int page, int pageSize, String name) {
        Page<Dish> dishPage = new Page<>(page,pageSize);
        Page<DishDTO> dishDTOPage = new Page<>();

        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        dishWrapper.like(Objects.nonNull(name),Dish::getName,name);
        dishWrapper.orderByDesc(Dish::getUpdateTime);
        dishMapper.selectPage(dishPage,dishWrapper);

        List<Dish> dishes = dishPage.getRecords();
        List<DishDTO> dishDTOS = dishes.stream().map((item -> {
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(item,dishDTO);
            Long categoryId = item.getCategoryId();
            Category category = categoryMapper.selectById(categoryId);
            dishDTO.setCategoryName(category.getName());
            return dishDTO;
        })).collect(Collectors.toList());

        dishDTOPage.setRecords(dishDTOS);
        return R.success(dishDTOPage);
    }

    @Cacheable(value = "DishCache",key = "'DishDTO'+#id")
    @Override
    public R<DishDTO> dishDTOById(Long id) {
        Dish dish = dishMapper.selectById(id);

        DishDTO dishDTO = new DishDTO();
        BeanUtils.copyProperties(dish,dishDTO);

        LambdaQueryWrapper<DishFlavor> dishFlavorWrapper = new LambdaQueryWrapper<>();
        dishFlavorWrapper.eq(DishFlavor::getDishId,id);
        List<DishFlavor> dishFlavors = dishFlavorMapper.selectList(dishFlavorWrapper);

        dishDTO.setFlavors(dishFlavors);

        return R.success(dishDTO);
    }

    @CacheEvict(value = "DishCache",allEntries = true)
    @Override
    public R<String> statusUpdateBiId(int status,String ids) {
        List<String> split = Arrays.asList(ids.split(","));

        List<Dish> dishList = new ArrayList<>();

        split.stream().map((item -> {
            Dish dish = new Dish();
            dish.setStatus(status);
            dish.setId(Long.valueOf(item));
            dishList.add(dish);
            return dishList;
        })).collect(Collectors.toList());

        dishList.stream().map(item -> {
            dishMapper.updateById(item);
            return null;
        }).collect(Collectors.toList());

        return R.success("成功");
    }

    @CacheEvict(value = "DishCache",allEntries = true)
    @Override
    public R<String> deleteByIds(String ids) {
        List<String> split = Arrays.asList(ids.split(","));

        split.stream().map(item -> {
            if (dishMapper.selectById(item).getStatus() == 0) {
                LambdaQueryWrapper<DishFlavor> longWrapper = new LambdaQueryWrapper<>();
                longWrapper.in(DishFlavor::getDishId,Long.valueOf(item));
                dishFlavorMapper.delete(longWrapper);
                dishMapper.deleteById(item);
            }
            return null;
        }).collect(Collectors.toList());

        return R.success("成功");
    }

    @Cacheable(value = "DishCache",key = "'List<DishDTO>'+#dish.categoryId")
    @Override
    public R<List<DishDTO>> listDishById(Dish dish) {

        List<DishDTO> dishDTOS = null;
        //动态设置redis key值  dish_CategoryId_Status
        /*String key = "dish_"+dish.getCategoryId()+"_"+dish.getStatus();

        dishDTOS = (List<DishDTO>) redisTemplate.opsForValue().get(key);
        //当不为空时表示redis中有缓存数据了 直接返回redis中的数据
        if (Objects.nonNull(dishDTOS)) {
            return R.success(dishDTOS);
        }*/

        //当redis中没有缓存数据 就查询数据库获取数据
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        dishWrapper.eq(Objects.nonNull(dish.getCategoryId()),Dish::getCategoryId,dish.getCategoryId());
        dishWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
        dishWrapper.eq(Dish::getStatus,1);

        List<Dish> dishList = dishMapper.selectList(dishWrapper);
        dishDTOS = dishList.stream().map(item -> {
            DishDTO dishDTO = new DishDTO();
            BeanUtils.copyProperties(item,dishDTO);

            Category category = categoryMapper.selectById(item.getCategoryId());

            if (Objects.nonNull(category)) {
                String name = category.getName();
                dishDTO.setCategoryName(name);
            }

            LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
            dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId,item.getId());
            List<DishFlavor> dishFlavors = dishFlavorMapper.selectList(dishFlavorLambdaQueryWrapper);
            dishDTO.setFlavors(dishFlavors);
            return dishDTO;
        }).collect(Collectors.toList());

        //把查询到的数据放入redis缓存中 设置60分钟有效时间
//        redisTemplate.opsForValue().set(key,dishDTOS,60, TimeUnit.MINUTES);

        return R.success(dishDTOS);
    }
}
