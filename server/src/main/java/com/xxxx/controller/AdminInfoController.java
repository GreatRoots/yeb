package com.xxxx.controller;

import com.xxxx.pojo.Admin;
import com.xxxx.pojo.AdminPassword;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IAdminService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminInfoController {

    @Resource
    private IAdminService adminService;

    @PutMapping("info")
    public RespInfo updateAdmin(@RequestBody Admin admin){
        return adminService.updateAdmin(admin);
    }

    @PutMapping("pass")
    public RespInfo updateAdminPassword(@RequestBody AdminPassword info){
        return adminService.updateAdminPassword(info);
    }
}
