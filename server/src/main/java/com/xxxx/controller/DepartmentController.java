package com.xxxx.controller;


import com.xxxx.pojo.Department;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IDepartmentService;
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
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Resource
    private IDepartmentService departmentService;

    @GetMapping
    public List<Department> queryAllDepartment(){
        return departmentService.queryAllDepartment();
    }

    @PostMapping
    public RespInfo addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @DeleteMapping("{id}")
    public RespInfo deleteDepartment(@PathVariable("id") Integer id){
        return departmentService.deleteDepartment(id);
    }
}
