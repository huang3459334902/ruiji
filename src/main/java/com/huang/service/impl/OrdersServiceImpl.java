package com.huang.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.BaseContest;
import com.huang.common.CustomException;
import com.huang.common.R;
import com.huang.dao.OrdersMapper;
import com.huang.entity.*;
import com.huang.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 订单表(Orders)表服务实现类
 *
 * @author makejava
 * @since 2022-11-12 14:23:19
 */
@Service("ordersService")
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {


    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressBookService addressBookService;
    
    /**
     * 根据ids删除多条数据
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            ordersMapper.deleteBatchIds(idList);
        }
    }

    @Override
    @Transactional
    public R<String> submit(Orders orders) {

        Long currentId = BaseContest.getCurrentId();
        LambdaQueryWrapper<ShoppingCart> shoppingCartWrapper = new LambdaQueryWrapper<>();
        shoppingCartWrapper.eq(ShoppingCart::getUserId,currentId);
        List<ShoppingCart> list = shoppingCartService.list(shoppingCartWrapper);
        if (Objects.isNull(list)) {
            throw new CustomException("购物车不能为空");
        }

        User user = userService.getById(currentId);


        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (Objects.isNull(addressBookId)) {
            throw new CustomException("用户地址信息错误");
        }

        long id = IdWorker.getId();

        AtomicInteger amount = new AtomicInteger(0);

        List<OrderDetail> orderDetails = list.stream().map(item -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(id);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet((int) (item.getAmount() * (new BigDecimal(item.getNumber())).intValue()));

            return orderDetail;
        }).collect(Collectors.toList());


        orders.setId(id);
        orders.setOrderTime(new Date());
        orders.setCheckoutTime(new Date());
        orders.setStatus(2);
        orders.setAmount(Double.valueOf(String.valueOf(amount)));
        orders.setUserId(currentId);
        orders.setNumber(String.valueOf(id));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ?"" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ?"" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ?"" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ?"" : addressBook.getDetail()));

        this.save(orders);
        orderDetailService.saveBatch(orderDetails);

        shoppingCartService.remove(shoppingCartWrapper);

        return R.success("成功");
    }


}
