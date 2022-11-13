package com.huang.controller;




import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huang.common.R;
import com.huang.entity.Employee;
import com.huang.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * 员工信息(Employee)表控制层
 *
 * @author makejava
 * @since 2022-11-04 10:57:26
 */
@Slf4j
@RestController
@RequestMapping("employee")
public class EmployeeController {
    /**
     * 服务对象
     */
    @Autowired
    private EmployeeService employeeService;


    //员工登录
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        return employeeService.list(request,employee);
    }

    //员工退出
    @PostMapping("/logout")
    public R logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    @PostMapping("")
    public R<String> save(@RequestBody Employee employee) {
        log.info("新增员工:{}",employee);

        return  employeeService.addEmployee(employee);
    }

    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name) {

        return employeeService.pageEmployee(page,pageSize,name);
    }

    @PutMapping("")
    public R<String> update(@RequestBody Employee employee) {

        return employeeService.update(employee);
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        return R.success(employeeService.getById(id));
    }


}

