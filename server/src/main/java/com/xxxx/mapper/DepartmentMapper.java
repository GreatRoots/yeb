package com.xxxx.mapper;

import com.xxxx.pojo.Department;
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
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> queryAllDepartment(Integer parentId);
}
