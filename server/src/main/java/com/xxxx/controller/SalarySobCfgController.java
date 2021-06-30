package com.xxxx.controller;

import com.xxxx.pojo.RespInfo;
import com.xxxx.pojo.RespPageBean;
import com.xxxx.pojo.Salary;
import com.xxxx.service.IEmployeeService;
import com.xxxx.service.ISalaryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/salary/sobcfg")
public class SalarySobCfgController {

    @Resource
    private ISalaryService salaryService;

    @Resource
    private IEmployeeService employeeService;

    @GetMapping
    public RespPageBean queryAllEmployeeSalary(Integer currentPage,Integer size){
        return employeeService.queryAllEmployeeSalary(currentPage,size);
    }

    @GetMapping("salaries")
    public List<Salary> queryAllSalary(){
        return salaryService.queryAllSalary();
    }

    @PutMapping
    public RespInfo updateEmployeeSalary(Integer eid, Integer sid){
        return employeeService.updateEmployeeSalary(eid,sid);
    }
}
