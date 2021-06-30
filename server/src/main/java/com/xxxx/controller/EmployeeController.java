package com.xxxx.controller;

import com.xxxx.pojo.*;
import com.xxxx.service.IEmployeeEcService;
import com.xxxx.service.IEmployeeService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
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
    private IEmployeeService employeeService;

    @GetMapping
    public RespPageBean queryAllEmployee(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer size,Employee employee, LocalDate[] beginDateScope){
        return employeeService.queryAllEmployee(currentPage,size,employee,beginDateScope);
    }

    @PostMapping
    public RespInfo addEmployee(@RequestBody Employee employee){
        return employeeService.addEmployee(employee);
    }

    @PutMapping
    public RespInfo updateEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public RespInfo deleteEmployee(@PathVariable("id") Integer id){
        return employeeService.deleteEmployee(id);
    }

    @GetMapping("deps")
    public List<Department> queryAllDepartment(){
        return employeeService.queryAllDepartment();
    }

    @GetMapping("joblevels")
    public List<Joblevel> queryAllJoblevel(){
        return employeeService.queryAllJoblevel();
    }

    @GetMapping("maxWorkID")
    public RespInfo queryAllWorkID(){
        return employeeService.queryAllWorkID();
    }

    @GetMapping("nations")
    public List<Nation> queryAllNation(){
        return employeeService.queryAllNation();
    }

    @GetMapping("politicsstatus")
    public List<PoliticsStatus> queryAllPoliticsStatus(){
        return employeeService.queryAllPoliticsStatus();
    }

    @GetMapping("positions")
    public List<Position> queryAllPosition(){
        return employeeService.queryAllPosition();
    }


}
