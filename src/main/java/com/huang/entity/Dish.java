package com.huang.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜品管理(Dish)表实体类
 *
 * @author makejava
 * @since 2022-11-05 20:28:18
 */
@Data
public class Dish implements Serializable {
    /**
     * 主键
     **/
    private Long id;
    
    /**
     * 菜品名称
     **/
    private String name;
    
    /**
     * 菜品分类id
     **/
    private Long categoryId;
    
    /**
     * 菜品价格
     **/
    private Double price;
    
    /**
     * 商品码
     **/
    private String code;
    
    /**
     * 图片
     **/
    private String image;
    
    /**
     * 描述信息
     **/
    private String description;
    
    /**
     * 0 停售 1 起售
     **/
    private Integer status;
    
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
    
    /**
     * 是否删除
     **/
    @TableLogic
    private Integer isDeleted;
    
}
