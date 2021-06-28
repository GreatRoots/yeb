package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.mapper.AdminRoleMapper;
import com.xxxx.mapper.MenuRoleMapper;
import com.xxxx.pojo.AdminRole;
import com.xxxx.pojo.MenuRole;
import com.xxxx.pojo.RespInfo;
import com.xxxx.pojo.Role;
import com.xxxx.mapper.RoleMapper;
import com.xxxx.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private MenuRoleMapper menuRoleMapper;

    @Override
    public List<Role> getRoleByAdminId(Integer id) {
        return roleMapper.getRoleByAdminId(id);
    }

    @Override
    public List<Role> queryAllRole() {
        return roleMapper.selectList(null);
    }

    @Override
    public RespInfo updateRole(Integer rid, Integer[] mids) {
        if (rid==null){
            return RespInfo.error("请选择角色");
        }
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        MenuRole menuRole=new MenuRole();
        menuRole.setRid(rid);
        for (Integer mid : mids) {
            menuRole.setMid(mid);
            if (menuRoleMapper.insert(menuRole)<1){
                return RespInfo.error("角色菜单添加失败");
            }
        }
        return RespInfo.success("角色菜单添加成功");
    }

}
