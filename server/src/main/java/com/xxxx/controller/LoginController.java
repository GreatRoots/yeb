package com.xxxx.controller;

import com.xxxx.pojo.Admin;
import com.xxxx.pojo.AdminLoginParam;
import com.xxxx.pojo.RespInfo;
import com.xxxx.pojo.Role;
import com.xxxx.service.IAdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
public class LoginController {

    @Resource
    private IAdminService adminService;

    @PostMapping("login")
    public RespInfo getLogin(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.getLogin(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    @GetMapping("admin/info")
    public Admin getAdmin(Principal principal){
        if (principal==null){
            return null;
        }
        Admin admin = adminService.getAdminByUsername(principal.getName());
        admin.setRoles(adminService.getAdminRolesById(admin.getId()));
        admin.setPassword(null);
        return admin;
    }

    @GetMapping("logout")
    public RespInfo logout(){
        return RespInfo.success("注销成功");
    }
}
