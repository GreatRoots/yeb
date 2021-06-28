package com.xxxx.controller;


import com.xxxx.pojo.*;
import com.xxxx.service.IAdminService;
import com.xxxx.service.IMenuService;
import com.xxxx.service.IRoleService;
import io.swagger.models.auth.In;
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
@RequestMapping("/system")
public class MenuController {

    @Resource
    private IMenuService menuService;

    @Resource
    private IAdminService adminService;

    @Resource
    private IRoleService roleService;

    @GetMapping("menu")
    public List<Menu> queryAllByUsername(){
        return menuService.queryAllByUsername();
    }

    @GetMapping("admin")
    public List<Admin> queryAllAdmin(String keywords){
        return adminService.queryAllAdmin(keywords);
    }

    @PutMapping("admin")
    public RespInfo updateAdmin(@RequestBody Admin admin){
        return adminService.updateAdmin(admin);
    }

    @DeleteMapping("admin/{id}")
    public RespInfo deleteAdminById(@PathVariable("id") Integer id){
        return adminService.deleteAdminById(id);
    }

    @PutMapping("admin/role")
    public RespInfo updateAdminRole( Integer adminId, Integer[] rids){
        return adminService.updateAdminRole(adminId,rids);
    }

    @GetMapping("admin/role")
    public List<Role> queryAdminAllRole(){
        return roleService.queryAllRole();
    }



}
