package com.xxxx.controller;

import com.xxxx.pojo.AdminGetLogin;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IAdminService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {
    @Resource
    private IAdminService adminService;
    @PostMapping("login")
    public RespInfo getLogin(@RequestBody AdminGetLogin adminGetLogin){
        return adminService.getLogin(adminGetLogin.getUsername(),adminGetLogin.getPassword());
    }
}
