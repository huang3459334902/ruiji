package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.AddressBook;

/**
 * 地址管理(AddressBook)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-11 20:43:05
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}
