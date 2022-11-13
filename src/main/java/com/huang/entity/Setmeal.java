package com.huang.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 套餐(Setmeal)表实体类
 *
 * @author makejava
 * @since 2022-11-05 20:28:28
 */
@Data
public class Setmeal implements Serializable {
    /**
     * 主键
     **/
    private Long id;
    
    /**
     * 菜品分类id
     **/
    private Long categoryId;
    
    /**
     * 套餐名称
     **/
    private String name;
    
    /**
     * 套餐价格
     **/
    private Double price;
    
    /**
     * 状态 0:停用 1:启用
     **/
    private Integer status;
    
    /**
     * 编码
     **/
    private String code;
    
    /**
     * 描述信息
     **/
    private String description;
    
    /**
     * 图片
     **/
    private String image;
    
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
