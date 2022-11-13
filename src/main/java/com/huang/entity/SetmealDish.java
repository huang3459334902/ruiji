package com.huang.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

import java.util.Date;

/**
 * 套餐菜品关系(SetmealDish)表实体类
 *
 * @author makejava
 * @since 2022-11-07 20:09:36
 */
@Data
public class SetmealDish {
    /**
     * 主键
     **/
    private Long id;
    
    /**
     * 套餐id 
     **/
    private Long setmealId;
    
    /**
     * 菜品id
     **/
    private Long dishId;
    
    /**
     * 菜品名称 （冗余字段）
     **/
    private String name;
    
    /**
     * 菜品原价（冗余字段）
     **/
    private Double price;
    
    /**
     * 份数
     **/
    private Integer copies;
    
    /**
     * 排序
     **/
    private Integer sort;
    
    /**
     * 创建时间
     **/
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    
    /**
     * 更新时间
     **/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    
    /**
     * 创建人
     **/
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;
    
    /**
     * 修改人
     **/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    
    /**
     * 是否删除
     **/
    @TableLogic
    private Integer isDeleted;
    
}
