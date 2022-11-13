package com.huang.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.common.R;
import com.huang.entity.Employee;

import javax.servlet.http.HttpServletRequest;

/**
 * 员工信息(Employee)表服务接口
 *
 * @author makejava
 * @since 2022-11-04 10:55:13
 */
public interface EmployeeService extends IService<Employee> {

    /**
     * 根据ids删除多条数据
     **/
    void deleteByIds(String ids);

    R<Employee> list(HttpServletRequest request, Employee employee);

    R<String> addEmployee(Employee employee);

    R<Page> pageEmployee(int page,int pageSize,String name);

    R<String> update(Employee employee);

}
