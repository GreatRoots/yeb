package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.xxxx.mapper.AdminRoleMapper;
import com.xxxx.pojo.Admin;
import com.xxxx.mapper.AdminMapper;
import com.xxxx.pojo.AdminRole;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private AdminMapper adminMapper;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private AdminRoleMapper adminRoleMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    public RespInfo getLogin(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isBlank(captcha)||!captcha.equals(code)) {
            return RespInfo.error("验证码错误");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails==null||passwordEncoder.matches(password,userDetails.getPassword())) {
            return RespInfo.error("用户名或密码错误");
        }
        if (!userDetails.isEnabled()) {
            return RespInfo.error("用户已禁用");
        }
        //将用户的基本信息存放在security
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtil.generateToken(userDetails);
        HashMap<String, String> info = new HashMap<>();
        info.put("token",token);
        info.put("tokenHead",tokenHead);
        return RespInfo.success(info);
    }


    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username));
    }

    @Override
    public List<Admin> queryAllAdmin(String keywords) {
        return adminMapper.queryAllAdmin(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(),keywords);
    }

    @Override
    public RespInfo updateAdmin(Admin admin) {
        if (adminMapper.updateAdmin(admin)<1){
            return RespInfo.error("更新失败");
        }
        return RespInfo.success("更新成功");
    }

    @Override
    public RespInfo updateAdminRole(Integer adminId, Integer[] rids) {
        if (adminId==null){
            return RespInfo.error("操作员为空");
        }
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        AdminRole adminRole=new AdminRole();
        adminRole.setAdminId(adminId);
        if (rids.length>0){
            for (Integer rid : rids) {
                adminRole.setRid(rid);
                if (adminRoleMapper.insert(adminRole)<1){
                    return RespInfo.error("更新角色失败");
                }
            }
        }
        return RespInfo.success("更新角色成功");
    }

    @Override
    public RespInfo deleteAdminById(Integer id) {
        if (id==null||adminMapper.selectById(id)==null){
            return RespInfo.error("未选择操作员或操作员不存在");
        }
        if (adminRoleMapper.selectList(new QueryWrapper<AdminRole>().eq("adminId", id)).size()>0) {
            adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", id));
        }
        return adminMapper.deleteById(id)>0?RespInfo.success("删除成功"):RespInfo.error("删除失败");
    }


//弃
//    @Override
//    public List<Role> getAdminRolesById(Integer id) {
//        return adminMapper.getAdminRolesById(id);
//    }
}
