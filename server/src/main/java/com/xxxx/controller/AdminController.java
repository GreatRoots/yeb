package com.xxxx.controller;


import com.xxxx.pojo.Admin;
import com.xxxx.pojo.RespInfo;
import com.xxxx.pojo.Role;
import com.xxxx.service.IAdminService;
import com.xxxx.service.IRoleService;
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
@RequestMapping("/system/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @Resource
    private IRoleService roleService;

    @GetMapping
    public List<Admin> queryAllAdmin(String keywords){
        return adminService.queryAllAdmin(keywords);
    }

    @PutMapping
    public RespInfo updateAdmin(@RequestBody Admin admin){
        return adminService.updateAdmin(admin);
    }

    @DeleteMapping("{id}")
    public RespInfo deleteAdminById(@PathVariable("id") Integer id){
        return adminService.deleteAdminById(id);
    }

    @PutMapping("role")
    public RespInfo updateAdminRole( Integer adminId, Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }

    @GetMapping("roles")
    public List<Role> queryAdminAllRole(){
        return roleService.queryAllRole();
    }
}
