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

    @GetMapping("menu")
    public List<Menu> queryAllByUsername(){
        return menuService.queryAllByUsername();
    }





}
