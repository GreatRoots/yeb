package com.xxxx.mapper;

import com.xxxx.pojo.Menu;
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
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> queryAllRoles();

    List<Menu> queryAllByAdminId(Integer id);

    List<Menu> queryAllMenu();
}
