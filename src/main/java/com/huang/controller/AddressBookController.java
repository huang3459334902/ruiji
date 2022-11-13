package com.huang.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.R;
import com.huang.entity.AddressBook;
import com.huang.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 地址管理(AddressBook)表控制层
 *
 * @author makejava
 * @since 2022-11-11 20:43:38
 */
@Slf4j
@RestController
@RequestMapping("addressBook")
public class AddressBookController {
    /**
     * 服务对象
     */
    @Autowired
    private AddressBookService addressBookService;

    @PostMapping("")
    public R<String> insertAddressBook(@RequestBody AddressBook addressBook) {
        addressBookService.save(addressBook);
        return R.success("添加成功");
    }

    @GetMapping("/list")
    public R<List<AddressBook>> list() {
        return R.success(addressBookService.list());
    }

    @PutMapping("/default")
    public R<String> updateByIdDefault(@RequestBody AddressBook addressBook) {
        addressBook.setIsDefault(1);

        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getIsDefault,1);
        AddressBook one = addressBookService.getOne(addressBookLambdaQueryWrapper);
        one.setIsDefault(0);
        addressBookService.updateById(one);

        addressBookService.updateById(addressBook);

        return R.success("成功");
    }

    @GetMapping("/default")
    public R<AddressBook> default1() {
        LambdaQueryWrapper<AddressBook> addressBookLambdaQueryWrapper = new LambdaQueryWrapper<>();
        addressBookLambdaQueryWrapper.eq(AddressBook::getIsDefault,1);
        return R.success(addressBookService.getOne(addressBookLambdaQueryWrapper));
    }

}

