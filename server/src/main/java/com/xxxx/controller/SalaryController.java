package com.xxxx.controller;


import com.xxxx.pojo.RespInfo;
import com.xxxx.pojo.Salary;
import com.xxxx.service.ISalaryService;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/salary/sob")
public class SalaryController {

    @Resource
    private ISalaryService salaryService;

    @GetMapping
    public List<Salary> queryAllSalary(){
        return salaryService.queryAllSalary();
    }

    @PostMapping
    public RespInfo addSalary(@RequestBody Salary salary){
        return salaryService.addSalary(salary);
    }

    @PutMapping
    public RespInfo updateSalary(@RequestBody Salary salary){
        return salaryService.updateSalary(salary);
    }

    @DeleteMapping("{id}")
    public RespInfo deleteSalary(@PathVariable("id") Integer id){
        return salaryService.deleteSalary(id);
    }

}
