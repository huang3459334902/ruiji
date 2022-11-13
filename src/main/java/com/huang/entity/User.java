package com.huang.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

import java.util.Date;

/**
 * 用户信息(User)表实体类
 *
 * @author makejava
 * @since 2022-11-10 18:59:33
 */
@Data
public class User {
    /**
     * 主键
     **/
    private Long id;
    
    /**
     * 姓名
     **/
    private String name;
    
    /**
     * 邮箱
     **/
    private String mail;

    
    /**
     * 性别
     **/
    private String sex;
    
    /**
     * 身份证号
     **/
    private String idNumber;
    
    /**
     * 头像
     **/
    private String avatar;
    
    /**
     * 状态 0:禁用，1:正常
     **/
    private Integer status;
    
}
