package com.xxxx.controller;

import com.xxxx.pojo.Admin;
import com.xxxx.service.IAdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chat/admin")
public class ChatController {

    @Resource
    private IAdminService adminService;

    @GetMapping
    public List<Admin> queryAllAdmin(String keywords){
        return adminService.queryAllAdmin(keywords);
    }
}
