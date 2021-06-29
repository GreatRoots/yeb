package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.mapper.EmployeeMapper;
import com.xxxx.pojo.Employee;
import com.xxxx.pojo.Joblevel;
import com.xxxx.mapper.JoblevelMapper;
import com.xxxx.pojo.Position;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IJoblevelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.naming.ldap.PagedResultsControl;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class JoblevelServiceImpl extends ServiceImpl<JoblevelMapper, Joblevel> implements IJoblevelService {

    @Resource
    private JoblevelMapper joblevelMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Joblevel> queryAllJoblevel() {
        return joblevelMapper.selectList(null);
    }

    @Override
    public RespInfo addJoblevel(Joblevel joblevel) {
        if (joblevel==null||joblevelMapper.selectOne(new QueryWrapper<Joblevel>().eq("name",joblevel.getName()))!=null){
            return RespInfo.error("职称已存在或属性错误");
        }
        joblevel.setCreateDate(LocalDateTime.now());
        joblevel.setId(null);
        return joblevelMapper.insert(joblevel)>0?RespInfo.success("职称添加成功"):RespInfo.error("职称添加失败");
    }

    @Override
    public RespInfo updateJoblevel(Joblevel joblevel) {
        if (joblevel==null||joblevel.getId()==null){
            return RespInfo.error("格式错误");
        }
        return joblevelMapper.updateById(joblevel)>0?RespInfo.success("职称更新成功"):RespInfo.error("职称更新失败");
    }

    @Override
    public RespInfo deleteMoreJoblevel(Integer[] ids) {
        if (ids.length == 0){
            return RespInfo.error("参数错误");
        }
        List<Integer> list=new ArrayList<>(ids.length);
        for (Integer id : ids) {
            List<Employee> posId = employeeMapper.selectList(new QueryWrapper<Employee>().eq("jobLevelId", id));
            if (posId.size()>0){
                for (Employee employee : posId) {
                    employee.setPosId(null);
                }
            }
            list.add(id);
        }
        joblevelMapper.deleteBatchIds(list);
        return RespInfo.success("删除职称信息成功");
    }

    @Override
    public RespInfo deleteOneJoblevel(Integer id) {
        return deleteMoreJoblevel(new Integer[]{id});
    }
}
