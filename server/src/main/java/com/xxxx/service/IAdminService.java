package com.xxxx.service;

import com.xxxx.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.RespInfo;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
public interface IAdminService extends IService<Admin> {

    RespInfo getLogin(String username, String password);

    Admin getAdminByUsername(String username);
}
