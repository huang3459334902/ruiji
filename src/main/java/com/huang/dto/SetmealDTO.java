package com.huang.dto;

import com.huang.entity.Setmeal;
import com.huang.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDTO extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
