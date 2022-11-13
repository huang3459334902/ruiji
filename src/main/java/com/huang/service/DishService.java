package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.R;
import com.huang.dto.DishDTO;
import com.huang.entity.Dish;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 菜品管理(Dish)表服务接口
 *
 * @author makejava
 * @since 2022-11-05 20:29:53
 */
public interface DishService extends IService<Dish> {

    R<String> saveWithFlavor(DishDTO dishDTO);

    R<Page> pageDish(int page, int pageSize, String name);

    R<DishDTO> dishDTOById( Long id);

    R<String> statusUpdateBiId(int status,String ids);

    R<String> deleteByIds(String ids);

    R<List<DishDTO>> listDishById(Dish dish);
}
