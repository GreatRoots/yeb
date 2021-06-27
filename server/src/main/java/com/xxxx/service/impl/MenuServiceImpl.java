package com.xxxx.service.impl;

import com.xxxx.pojo.Admin;
import com.xxxx.pojo.Menu;
import com.xxxx.mapper.MenuMapper;
import com.xxxx.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public List<Menu> queryAllRoles() {
        return menuMapper.queryAllRoles();
    }

    @Override
    public List<Menu> queryAllByUsername() {
        return menuMapper.queryAllByAdminId(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
    }
}
