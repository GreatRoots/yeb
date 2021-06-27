package com.xxxx.service;

import com.xxxx.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
public interface IRoleService extends IService<Role> {

    List<Role> getRoleByAdminId(Integer id);
}
