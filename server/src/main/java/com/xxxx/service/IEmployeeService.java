package com.xxxx.service;

import com.xxxx.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
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
public interface IEmployeeService extends IService<Employee> {

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

    RespPageBean queryAllEmployeeSalary(Integer currentPage, Integer size);

    RespInfo updateEmployeeSalary(Integer eid, Integer sid);

    void exportEmployee(HttpServletResponse response);
}
