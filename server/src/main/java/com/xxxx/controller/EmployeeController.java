package com.xxxx.controller;

import com.xxxx.pojo.*;
import com.xxxx.service.IEmployeeEcService;
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
    private IEmployeeEcService employeeEcService;

    @GetMapping
    public RespPageBean queryAllEmployee(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer size,Employee employee, LocalDate[] beginDateScope){
        return employeeEcService.queryAllEmployee(currentPage,size,employee,beginDateScope);
    }

    @PostMapping
    public RespInfo addEmployee(@RequestBody Employee employee){
        return employeeEcService.addEmployee(employee);
    }

    @PutMapping
    public RespInfo updateEmployee(@RequestBody Employee employee){
        return employeeEcService.updateEmployee(employee);
    }

    @DeleteMapping("{id}")
    public RespInfo deleteEmployee(@PathVariable("id") Integer id){
        return employeeEcService.deleteEmployee(id);
    }

    @GetMapping("deps")
    public List<Department> queryAllDepartment(){
        return employeeEcService.queryAllDepartment();
    }

    @GetMapping("joblevels")
    public List<Joblevel> queryAllJoblevel(){
        return employeeEcService.queryAllJoblevel();
    }

    @GetMapping("maxWorkID")
    public RespInfo queryAllWorkID(){
        return employeeEcService.queryAllWorkID();
    }

    @GetMapping("nations")
    public List<Nation> queryAllNation(){
        return employeeEcService.queryAllNation();
    }

    @GetMapping("politicsstatus")
    public List<PoliticsStatus> queryAllPoliticsStatus(){
        return employeeEcService.queryAllPoliticsStatus();
    }

    @GetMapping("positions")
    public List<Position> queryAllPosition(){
        return employeeEcService.queryAllPosition();
    }


}
