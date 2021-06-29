package com.xxxx.controller;


import com.xxxx.pojo.Employee;
import com.xxxx.service.IEmployeeEcService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Resource
    private IEmployeeEcService employeeEcService;

    @GetMapping
    public List<Employee> queryAllEmployee(){
        return employeeEcService.queryAllEmployee();
    }
}
