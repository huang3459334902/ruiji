package com.huang.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.huang.entity.Employee;

/**
 * 员工信息(Employee)表数据库访问层
 *
 * @author makejava
 * @since 2022-11-04 10:54:57
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}
