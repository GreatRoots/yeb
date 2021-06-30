package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.mapper.*;
import com.xxxx.pojo.*;
import com.xxxx.service.IEmployeeEcService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Arrays;
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

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private JoblevelMapper joblevelMapper;

    @Resource
    private NationMapper nationMapper;

    @Resource
    private PoliticsStatusMapper politicsStatusMapper;

    @Resource
    private PositionMapper positionMapper;

    @Override
    public RespPageBean queryAllEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        IPage<Employee> page = employeeMapper.queryAllEmployeeByPage(new Page<>(currentPage, size),employee,beginDateScope);
        return new RespPageBean(page.getRecords(),page.getTotal());
    }

    @Override
    public RespInfo addEmployee(Employee employee) {
        employee.setContractTerm(setContractTerm(employee.getBeginContract(),employee.getEndContract()));
        return employeeMapper.insert(employee)>0?RespInfo.success("添加员工成功"):RespInfo.error("添加员工失败");
    }

    @Override
    public List<Department> queryAllDepartment() {
        return departmentMapper.queryAllDepartment(-1);
    }

    @Override
    public List<Joblevel> queryAllJoblevel() {
        return joblevelMapper.selectList(null);
    }

    @Override
    public RespInfo queryAllWorkID() {
        return RespInfo.success("获取工号成功",String.format("%08d",Integer.parseInt(employeeMapper.selectOne(new QueryWrapper<Employee>().orderByDesc("workID").last("limit 1")).getWorkID())+1));
    }

    @Override
    public List<Nation> queryAllNation() {
        return nationMapper.selectList(null);
    }

    @Override
    public List<PoliticsStatus> queryAllPoliticsStatus() {
        return politicsStatusMapper.selectList(null);
    }

    @Override
    public List<Position> queryAllPosition() {
        return positionMapper.selectList(null);
    }

    @Override
    public RespInfo updateEmployee(Employee employee) {
        employee.setContractTerm(setContractTerm(employee.getBeginContract(),employee.getEndContract()));
        return employeeMapper.updateById(employee)>0?RespInfo.success("更新员工成功"):RespInfo.error("更新员工失败");
    }

    @Override
    public RespInfo deleteEmployee(Integer id) {
        return employeeMapper.deleteById(id)>0?RespInfo.success("删除员工成功"):RespInfo.error("删除员工失败");
    }

    private Double setContractTerm(LocalDate beginContract, LocalDate endContract) {
        Period period = Period.between(beginContract, endContract);
        return Double.valueOf(String.format("%.2f",period.getYears()+period.getMonths()/12d+period.getDays()/365d));
    }

}
