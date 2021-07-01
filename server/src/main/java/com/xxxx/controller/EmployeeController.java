package com.xxxx.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.xxxx.pojo.*;
import com.xxxx.service.IEmployeeEcService;
import com.xxxx.service.IEmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
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

    @ApiOperation(value = "导出员工数据")
    @GetMapping(value = "/export", produces = "application/octet-stream")
    public void exportEmployee(HttpServletResponse response) {
        employeeService.exportEmployee(response);
    }
}
