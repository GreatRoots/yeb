package com.xxxx.service.impl;

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

    @Override
    public List<Role> getRoleByAdminId(Integer id) {
        return roleMapper.getRoleByAdminId(id);
    }
}
