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

    @Resource
    private AdminRoleMapper adminRoleMapper;

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
        if (mids!=null&&mids.length>0){
            MenuRole menuRole=new MenuRole();
            menuRole.setRid(rid);
            for (Integer mid : mids) {
                menuRole.setMid(mid);
                if (menuRoleMapper.insert(menuRole)<1){
                    return RespInfo.error("角色菜单添加失败");
                }
            }
        }
        return RespInfo.success("角色菜单添加成功");
    }

    @Override
    public RespInfo addRole(Role role) {
        if (role==null||role.getName().startsWith("ROLE_")){
            return RespInfo.error("信息为空或格式错误");
        }
        List<Role> roles = roleMapper.selectList(null);
        for (Role role1 : roles) {
            if (role.getName().equals(role1.getName())||role.getNameZh().equals(role1.getNameZh())){
                return RespInfo.error("角色已存在");
            }
        }
        role.setId(null);
        return roleMapper.insert(role)>0?RespInfo.success("角色添加成功"):RespInfo.error("角色添加失败");
    }

    @Override
    public RespInfo deleteRole(Integer rid) {
        if (rid==null||roleMapper.selectById(rid)==null){
            return RespInfo.error("未选择角色或角色不存在");
        }
        if (menuRoleMapper.selectList(new QueryWrapper<MenuRole>().eq("rid",rid)).size()>0) {
            menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        }
        if (adminRoleMapper.selectList(new QueryWrapper<AdminRole>().eq("rid",rid)).size()>0) {
            adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("rid",rid));
        }
        return roleMapper.deleteById(rid)>0?RespInfo.success("角色删除成功"):RespInfo.error("角色删除失败");
    }

}
