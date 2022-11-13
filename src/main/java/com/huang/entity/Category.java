package com.huang.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜品及套餐分类(Category)表实体类
 *
 * @author makejava
 * @since 2022-11-05 19:33:36
 */
@Data
public class Category implements Serializable {
    /**
     * 主键
     **/
    private Long id;
    
    /**
     * 类型   1 菜品分类 2 套餐分类
     **/
    private Integer type;
    
    /**
     * 分类名称
     **/
    private String name;
    
    /**
     * 顺序
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
    
}
