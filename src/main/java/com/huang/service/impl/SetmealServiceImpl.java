package com.huang.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.CustomException;
import com.huang.common.R;
import com.huang.dao.CategoryMapper;
import com.huang.dao.DishMapper;
import com.huang.dao.SetmealDishMapper;
import com.huang.dao.SetmealMapper;
import com.huang.dto.SetmealDTO;
import com.huang.entity.Category;
import com.huang.entity.Dish;
import com.huang.entity.Setmeal;
import com.huang.entity.SetmealDish;
import com.huang.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 套餐(Setmeal)表服务实现类
 *
 * @author makejava
 * @since 2022-11-05 20:29:59
 */
@Service("setmealService")
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {


    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Autowired
    private CategoryMapper categoryMapper;
    
    /**
     * 根据ids删除多条数据
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            setmealMapper.deleteBatchIds(idList);
        }
    }

    @Override
    public R<String> saveSetmeal(SetmealDTO setmealDTO) {

        setmealMapper.insert(setmealDTO);

        setmealDTO.getSetmealDishes().stream().map(item -> {
            item.setSetmealId(setmealDTO.getId());
            setmealDishMapper.insert(item);
            return null;
        }).collect(Collectors.toList());

        return R.success("添加成功");
    }

    @Override
    public R<Page> page(int page, int pageSize, String name) {
        Page<Setmeal> setmealPage = new Page<>(page, pageSize);
        Page<SetmealDTO> setmealDTOPage = new Page<>();

        LambdaQueryWrapper<Setmeal> setmealWrapper = new LambdaQueryWrapper<>();
        setmealWrapper.like(Objects.nonNull(name),Setmeal::getName,name);
        setmealMapper.selectPage(setmealPage,setmealWrapper);

        List<Setmeal> records = setmealPage.getRecords();
        List<SetmealDTO> setmealDTOS = records.stream().map(item -> {
            SetmealDTO setmealDTO = new SetmealDTO();
            BeanUtils.copyProperties(item,setmealDTO);

            Category category = categoryMapper.selectById(item.getCategoryId());
            if (Objects.nonNull(category)) {
                setmealDTO.setCategoryName(category.getName());
            }
            return setmealDTO;
        }).collect(Collectors.toList());

        setmealDTOPage.setRecords(setmealDTOS);

        return R.success(setmealDTOPage);
    }

    @Override
    public R<String> deleteSetmealByIds(String ids) {
        List<String> split = Arrays.asList(ids.split(","));

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.in(Setmeal::getId,split);
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus,1);
        int count = setmealMapper.selectCount(setmealLambdaQueryWrapper);
        if (count > 0) {
            throw new CustomException("不能删除");
        }

        setmealMapper.deleteBatchIds(split);
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishLambdaQueryWrapper.in(SetmealDish::getSetmealId,split);
        setmealDishMapper.delete(setmealDishLambdaQueryWrapper);

        return R.success("删除成功");
    }


}
