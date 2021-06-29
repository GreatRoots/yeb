package com.xxxx.controller;

import com.xxxx.pojo.Admin;
import com.xxxx.pojo.Menu;
import com.xxxx.pojo.RespInfo;
import com.xxxx.pojo.Role;
import com.xxxx.service.IAdminService;
import com.xxxx.service.IMenuRoleService;
import com.xxxx.service.IMenuService;
import com.xxxx.service.IRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {

    @Resource
    public IMenuService menuService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IMenuRoleService menuRoleService;

    @GetMapping
    public List<Role> queryAllRole(){
        return roleService.queryAllRole();
    }

    @PutMapping
    public RespInfo updateRole(Integer rid, Integer[] mids){
        return roleService.updateRole(rid,mids);
    }

    @GetMapping("menus")
    public List<Menu> queryAllMenu(){
        return menuService.queryAllMenu();
    }

    @GetMapping("mid/{rid}")
    public List<Integer> queryMenuIdByRoleId(@PathVariable("rid") Integer rid){
        if (rid==null){
            return null;
        }
        return menuRoleService.queryMenuIdByRoleId(rid);
    }

    @PostMapping("role")
    public RespInfo addRole(@RequestBody Role role){
        return roleService.addRole(role);
    }

    @DeleteMapping("role/{rid}")
    public RespInfo deleteRole(@PathVariable("rid") Integer rid){
        return roleService.deleteRole(rid);
    }




}
