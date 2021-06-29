package com.xxxx.service.impl;

import com.xxxx.mapper.EmployeeMapper;
import com.xxxx.pojo.Employee;
import com.xxxx.pojo.EmployeeEc;
import com.xxxx.mapper.EmployeeEcMapper;
import com.xxxx.service.IEmployeeEcService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
@Service
public class EmployeeEcServiceImpl extends ServiceImpl<EmployeeEcMapper, EmployeeEc> implements IEmployeeEcService {

    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> queryAllEmployee() {
        return employeeMapper.selectList(null);
    }
}
