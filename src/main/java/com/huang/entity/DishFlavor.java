package com.huang.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

import java.util.Date;

/**
 * 菜品口味关系表(DishFlavor)表实体类
 *
 * @author makejava
 * @since 2022-11-06 15:13:07
 */
@Data
public class DishFlavor {
    /**
     * 主键
     **/
    private Long id;
    
    /**
     * 菜品
     **/
    private Long dishId;
    
    /**
     * 口味名称
     **/
    private String name;
    
    /**
     * 口味数据list
     **/
    private String value;
    
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
