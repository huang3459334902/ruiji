package com.huang.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单明细表(OrderDetail)表实体类
 *
 * @author makejava
 * @since 2022-11-12 15:07:53
 */
@Data
public class OrderDetail implements Serializable {
    /**
     * 主键
     **/
    private Long id;
    
    /**
     * 名字
     **/
    private String name;
    
    /**
     * 图片
     **/
    private String image;
    
    /**
     * 订单id
     **/
    private Long orderId;
    
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
    
}
