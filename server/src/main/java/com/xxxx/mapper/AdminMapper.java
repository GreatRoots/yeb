package com.xxxx.mapper;

import com.xxxx.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
public interface AdminMapper extends BaseMapper<Admin> {

    List<Role> getAdminRolesById(Integer id);
}
