package com.huang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.entity.AddressBook;

/**
 * 地址管理(AddressBook)表服务接口
 *
 * @author makejava
 * @since 2022-11-11 20:43:10
 */
public interface AddressBookService extends IService<AddressBook> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);
    
    
}
