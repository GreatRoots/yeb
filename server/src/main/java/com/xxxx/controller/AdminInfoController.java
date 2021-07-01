package com.xxxx.controller;

import com.xxxx.pojo.Admin;
import com.xxxx.pojo.AdminPassword;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IAdminService;
import com.xxxx.utils.FastDFSUtils;
import io.swagger.models.auth.In;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

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

    @PostMapping("userface")
    public RespInfo updateUserFace(MultipartFile file, Integer id){
        return adminService.updateUserFace(file,id);
    }
}
