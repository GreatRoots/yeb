package com.xxxx.service;

import com.xxxx.pojo.Menu;
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
public interface IMenuService extends IService<Menu> {

    List<Menu> queryAllRoles();

    List<Menu> queryAllByUsername();

    List<Menu> queryAllMenu();
}
