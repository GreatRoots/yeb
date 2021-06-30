package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.pojo.RespInfo;
import com.xxxx.pojo.Salary;
import com.xxxx.mapper.SalaryMapper;
import com.xxxx.service.ISalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements ISalaryService {

    @Resource
    private SalaryMapper salaryMapper;

    @Override
    public List<Salary> queryAllSalary() {
        return salaryMapper.selectList(null);
    }

    @Override
    public RespInfo addSalary(Salary salary) {
        if (salaryMapper.selectOne(new QueryWrapper<Salary>().eq("name", salary.getName())) !=null) {
            return RespInfo.error("账套名称已存在");
        }
        salary.setCreateDate(LocalDateTime.now());
        return salaryMapper.insert(salary)>0?RespInfo.success("添加账套成功"):RespInfo.error("添加账套失败");
    }

    @Override
    public RespInfo updateSalary(Salary salary) {
        if (salaryMapper.selectById(salary.getId()).getName().equals(salary.getName())){
            return salaryMapper.updateById(salary)>0?RespInfo.success("更新账套成功"):RespInfo.error("更新账套失败");
        }else {
            if (salaryMapper.selectOne(new QueryWrapper<Salary>().eq("name", salary.getName())) !=null) {
                return RespInfo.error("账套名称已存在");
            }
            return salaryMapper.updateById(salary)>0?RespInfo.success("更新账套成功"):RespInfo.error("更新账套失败");
        }
    }

    @Override
    public RespInfo deleteSalary(Integer id) {
        if (id==null) {
            return RespInfo.error("账套删除失败");
        }
        return salaryMapper.deleteById(id)>0?RespInfo.success("删除账套成功"):RespInfo.error("删除账套失败");
    }
}
