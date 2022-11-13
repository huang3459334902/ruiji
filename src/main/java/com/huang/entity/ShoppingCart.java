package com.huang.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

import java.util.Date;

/**
 * 购物车(ShoppingCart)表实体类
 *
 * @author makejava
 * @since 2022-11-11 21:53:40
 */
@Data
public class ShoppingCart {
    /**
     * 主键
     **/
    private Long id;
    
    /**
     * 名称
     **/
    private String name;
    
    /**
     * 图片
     **/
    private String image;
    
    /**
     * 主键
     **/
    @TableField(fill = FieldFill.INSERT)
    private Long userId;
    
    /**
     * 菜品id
     **/
    private Long dishId;
    
    /**
     * 套餐id
     **/
    private Long setmealId;
    
    /**
     * 口味
     **/
    private String dishFlavor;
    
    /**
     * 数量
     **/
    private Integer number;
    
    /**
     * 金额
     **/
    private Double amount;
    
    /**
     * 创建时间
     **/
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
}
