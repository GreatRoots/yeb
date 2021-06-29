package com.xxxx.service.impl;

import com.xxxx.pojo.Admin;
import com.xxxx.pojo.Menu;
import com.xxxx.mapper.MenuMapper;
import com.xxxx.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    
    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<Menu> queryAllRoles() {
        return menuMapper.queryAllRoles();
    }

    @Override
    public List<Menu> queryAllByUsername() {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Integer id = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        //查询缓存中是否有数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + id);
        if (CollectionUtils.isEmpty(menus)){
            //如果没数据，数据库中查询，并设置到缓存中
            menus = menuMapper.queryAllByAdminId(id);
            valueOperations.set("menu_"+id,menus);
        }
        return menus;
    }

    @Override
    public List<Menu> queryAllMenu() {
        return menuMapper.queryAllMenu();
    }
}
