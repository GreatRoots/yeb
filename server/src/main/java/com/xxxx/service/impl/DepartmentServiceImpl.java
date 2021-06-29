package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.mapper.EmployeeMapper;
import com.xxxx.pojo.Department;
import com.xxxx.mapper.DepartmentMapper;
import com.xxxx.pojo.Employee;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Department> queryAllDepartment() {
        return departmentMapper.queryAllDepartment(-1);
    }

    @Override
    public RespInfo addDepartment(Department department) {
        if (department==null) {
            return RespInfo.error("属性为空");
        }
        List<Department> departments = departmentMapper.selectList(null);
        for (Department department1 : departments) {
            if (department.getName().equals(department1.getName())) {
                return RespInfo.error("部门已存在");
            }
        }
        department.setId(null);
        return departmentMapper.insert(department)>0?RespInfo.success("部门添加成功"):RespInfo.error("部门添加失败");
    }

    @Override
    public RespInfo deleteDepartment(Integer id) {
        if (id==null||departmentMapper.selectById(id)==null){
            return RespInfo.error("未选择或部门不存在");
        }
        List<Employee> departmentId = employeeMapper.selectList(new QueryWrapper<Employee>().eq("departmentId", id));
        if (departmentId.size()>0){
            for (Employee employee : departmentId) {
                employee.setDepartmentId(null);
            }
        }
        return departmentMapper.deleteById(id)>0?RespInfo.success("部门删除成功"):RespInfo.error("部门删除失败");
    }
}
