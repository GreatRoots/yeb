package com.xxxx.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExcelBaseParams;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xxxx.mapper.*;
import com.xxxx.pojo.*;
import com.xxxx.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.Period;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

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

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public RespPageBean queryAllEmployee(Integer currentPage, Integer size, Employee employee, LocalDate[] beginDateScope) {
        IPage<Employee> page = employeeMapper.queryAllEmployeeByPage(new Page<>(currentPage, size),employee,beginDateScope);
        return new RespPageBean(page.getRecords(),page.getTotal());
    }

    @Override
    public RespInfo addEmployee(Employee employee) {
        employee.setContractTerm(setContractTerm(employee.getBeginContract(),employee.getEndContract()));
        if (employeeMapper.insert(employee) > 0) {
            rabbitTemplate.convertAndSend("mail.server",employee);
            RespInfo.success("添加员工成功");
        } else {
            RespInfo.error("添加员工失败");
        }
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

    @Override
    public RespPageBean queryAllEmployeeSalary(Integer currentPage, Integer size) {
        Page<Employee> employeePage = new Page<>(currentPage, size);
        IPage<Employee> iPage=employeeMapper.queryAllEmployeeSalary(employeePage);
        return new RespPageBean(iPage.getRecords(),iPage.getTotal());
    }

    @Override
    public RespInfo updateEmployeeSalary(Integer eid, Integer sid) {
        if (eid==null){
            return RespInfo.error("未选择员工");
        }
        Employee employee = employeeMapper.selectById(eid);
        if (employee==null){
            return RespInfo.error("员工不存在");
        }
        employee.setSalaryId(sid);
        return employeeMapper.updateById(employee)>0?RespInfo.success("更新员工账套成功"):RespInfo.error("更新员工账套失败");
    }

    @Override
    public void exportEmployee(HttpServletResponse response) {
        List<Employee> employees = employeeMapper.selectList(null);
        ExportParams exportParams = new ExportParams("员工表", "员工表", ExcelType.HSSF);
        Workbook book = ExcelExportUtil.exportExcel(exportParams, Employee.class, employees);
        ServletOutputStream out = null;
        try {
            //流形式
            response.setHeader("content-type","application/octet-stream");
            //防止中文乱码
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("员工表.xls","UTF-8"));
            out = response.getOutputStream();
            book.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Double setContractTerm(LocalDate beginContract, LocalDate endContract) {
        Period period = Period.between(beginContract, endContract);
        return Double.valueOf(String.format("%.2f",period.getYears()+period.getMonths()/12d+period.getDays()/365d));
    }
}
