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
@RequestMapping("system/basic")
public class PermissController {

    @Resource
    public IMenuService menuService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IMenuRoleService menuRoleService;

    @GetMapping("permiss")
    public List<Role> queryAllRole(){
        return roleService.queryAllRole();
    }

    @PutMapping("permiss")
    public RespInfo updateRole(Integer rid, Integer[] mids){
        return roleService.updateRole(rid,mids);
    }

    @GetMapping("permiss/menus")
    public List<Menu> queryAllMenu(){
        return menuService.queryAllMenu();
    }

    @GetMapping("permiss/mid/{rid}")
    public List<Integer> queryMenuIdByRoleId(@PathVariable("rid") Integer rid){
        if (rid==null){
            return null;
        }
        return menuRoleService.queryMenuIdByRoleId(rid);
    }


}
