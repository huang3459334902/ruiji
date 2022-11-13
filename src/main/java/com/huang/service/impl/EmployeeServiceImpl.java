package com.huang.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.common.BaseContest;
import com.huang.common.R;
import com.huang.dao.EmployeeMapper;
import com.huang.entity.Employee;
import com.huang.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.unit.DataUnit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 员工信息(Employee)表服务实现类
 *
 * @author makejava
 * @since 2022-11-04 10:55:13
 */
@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;
    
    /**
     * 根据ids删除多条数据
     **/
    @Override
    public void deleteByIds(String ids) {
        String[] idsArr = ids.split(",");
        if (idsArr.length > 0) {
            List<Long> idList = Stream.of(idsArr).map(Long::valueOf).collect(Collectors.toList());
            employeeMapper.deleteBatchIds(idList);
        }
    }

    @Override
    public R<Employee> list(HttpServletRequest request, Employee employee) {
        //密码加密
        String s = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());

        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername,employee.getUsername());

        Employee employee1 = employeeMapper.selectOne(wrapper);

        if (employee1 == null) {
            return R.error("登录失败");
        }
        if (!employee1.getPassword().equals(s)) {
            return R.error("密码错误");
        }
        if (employee1.getStatus() == 0) {
            return R.error("账号已冻结");
        }
        request.getSession().setAttribute("employee",employee1.getId());


        return R.success(employee1);
    }

    @Override
    public R<String> addEmployee(Employee employee) {
        String s = DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setPassword(s);


        employeeMapper.insert(employee);
        return R.success("新增成功");
    }

    @Override
    public R<Page> pageEmployee(int page, int pageSize, String name) {

        Page<Employee> employeePage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        //条件过滤
        wrapper.like(Objects.nonNull(name),Employee::getName,name);
        //排序条件
        wrapper.orderByDesc(Employee::getUpdateTime);
        employeeMapper.selectPage(employeePage,wrapper);

        return R.success(employeePage);
    }

    @Override
    public R<String> update(Employee employee) {


        employeeMapper.updateById(employee);

        return R.success("修改成功");
    }


}
