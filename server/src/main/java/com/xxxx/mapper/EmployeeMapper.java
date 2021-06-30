package com.xxxx.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    IPage<Employee> queryAllEmployeeByPage(Page<Employee> page, @Param("employee") Employee employee,
                                           @Param("beginDateScope") LocalDate[] beginDateScope);

    IPage<Employee> queryAllEmployeeSalary(Page<Employee> page);
}
