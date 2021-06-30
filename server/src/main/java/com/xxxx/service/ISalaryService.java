package com.xxxx.service;

import com.xxxx.pojo.RespInfo;
import com.xxxx.pojo.Salary;
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
public interface ISalaryService extends IService<Salary> {


    List<Salary> queryAllSalary();

    RespInfo addSalary(Salary salary);

    RespInfo updateSalary(Salary salary);

    RespInfo deleteSalary(Integer id);
}
