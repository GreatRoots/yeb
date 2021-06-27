package com.xxxx.controller;

import com.xxxx.pojo.Admin;
import com.xxxx.pojo.AdminGetLogin;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IAdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;

@RestController
public class LoginController {

    @Resource
    private IAdminService adminService;

    @PostMapping("login")
    public RespInfo getLogin(@RequestBody AdminGetLogin adminGetLogin){
        return adminService.getLogin(adminGetLogin.getUsername(),adminGetLogin.getPassword());
    }

    @GetMapping("admin/info")
    public Admin getAdmin(Principal principal){
        if (principal==null) {
            return null;
        }
        Admin admin = adminService.getAdminByUsername(principal.getName());
        admin.setPassword(null);
        return admin;
    }

    @GetMapping("logout")
    public RespInfo logout(){
        return RespInfo.success("注销成功");
    }
}
