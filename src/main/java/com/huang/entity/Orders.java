package com.huang.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import lombok.Data;

import java.util.Date;

/**
 * 订单表(Orders)表实体类
 *
 * @author makejava
 * @since 2022-11-12 14:23:10
 */
@Data
public class Orders {
    /**
     * 主键
     **/
    private Long id;
    
    /**
     * 订单号
     **/
    private String number;
    
    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     **/
    private Integer status;
    
    /**
     * 下单用户
     **/
    private Long userId;
    
    /**
     * 地址id
     **/
    private Long addressBookId;
    
    /**
     * 下单时间
     **/
    @TableField(fill = FieldFill.INSERT)
    private Date orderTime;
    
    /**
     * 结账时间
     **/
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date checkoutTime;
    
    /**
     * 支付方式 1微信,2支付宝
     **/
    private Integer payMethod;
    
    /**
     * 实收金额
     **/
    private Double amount;
    
    /**
     * 备注
     **/
    private String remark;
    
    private String phone;
    
    private String address;
    
    private String userName;
    
    private String consignee;
    
}
