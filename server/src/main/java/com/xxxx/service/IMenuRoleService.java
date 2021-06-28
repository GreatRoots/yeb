package com.xxxx.service;

import com.xxxx.pojo.MenuRole;
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
public interface IMenuRoleService extends IService<MenuRole> {

    List<Integer> queryMenuIdByRoleId(Integer rid);
}
