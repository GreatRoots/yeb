package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.pojo.MenuRole;
import com.xxxx.mapper.MenuRoleMapper;
import com.xxxx.service.IMenuRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Resource
    private MenuRoleMapper menuRoleMapper;

    @Override
    public List<Integer> queryMenuIdByRoleId(Integer rid) {
        List<MenuRole> menuRoles = menuRoleMapper.selectList(new QueryWrapper<MenuRole>().eq("rid", rid));
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < menuRoles.size(); i++) {
            list.add(menuRoles.get(i).getMid());
        }
        return list;
    }
}
