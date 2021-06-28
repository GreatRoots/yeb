package com.xxxx.mapper;

import com.xxxx.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoleByAdminId(Integer id);

}
