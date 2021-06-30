package com.xxxx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xxxx.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
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

    RespPageBean queryAllEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope);

    RespInfo addEmployee(Employee employee);

    List<Department> queryAllDepartment();

    List<Joblevel> queryAllJoblevel();

    RespInfo queryAllWorkID();

    List<Nation> queryAllNation();

    List<PoliticsStatus> queryAllPoliticsStatus();

    List<Position> queryAllPosition();

    RespInfo updateEmployee(Employee employee);

    RespInfo deleteEmployee(Integer id);
}
