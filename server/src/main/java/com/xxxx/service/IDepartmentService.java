package com.xxxx.service;

import com.xxxx.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.RespInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> queryAllDepartment();

    RespInfo addDepartment(Department department);

    RespInfo deleteDepartment(Integer id);
}
