package com.xxxx.service;

import com.xxxx.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.RespInfo;
import com.xxxx.pojo.Role;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
public interface IAdminService extends IService<Admin> {

    RespInfo getLogin(String username, String password, String code, HttpServletRequest request);

    Admin getAdminByUsername(String username);

    List<Admin> queryAllAdmin(String keywords);

    RespInfo updateAdmin(Admin admin);

    RespInfo updateAdminRole(Integer adminId, Integer[] rids);

    RespInfo deleteAdminById(Integer id);


//    List<Role> getAdminRolesById(Integer id);
}
