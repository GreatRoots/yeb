package com.xxxx.service;

import com.xxxx.pojo.Employee;
import com.xxxx.pojo.EmployeeEc;
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
public interface IEmployeeEcService extends IService<EmployeeEc> {

    List<Employee> queryAllEmployee();
}
