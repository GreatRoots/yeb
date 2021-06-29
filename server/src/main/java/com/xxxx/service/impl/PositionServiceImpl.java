package com.xxxx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.mapper.EmployeeMapper;
import com.xxxx.pojo.Employee;
import com.xxxx.pojo.Position;
import com.xxxx.mapper.PositionMapper;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {

    @Resource
    private PositionMapper positionMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public List<Position> queryAllPosition() {
        return positionMapper.selectList(null);
    }

    @Override
    public RespInfo addPosition(Position position) {
        if (position==null||positionMapper.selectOne(new QueryWrapper<Position>().eq("name",position.getName()))!=null){
            return RespInfo.error("职位已存在或属性错误");
        }
        position.setCreateDate(LocalDateTime.now());
        position.setId(null);
        return positionMapper.insert(position)>0?RespInfo.success("职位添加成功"):RespInfo.error("职位添加失败");
    }

    @Override
    public RespInfo updatePosition(Position position) {
        if (position==null||position.getId()==null){
            return RespInfo.error("格式错误");
        }
        return positionMapper.updateById(position)>0?RespInfo.success("职位更新成功"):RespInfo.error("职位更新失败");
    }

    @Override
    public RespInfo deleteMorePosition(Integer[] ids) {
        if (ids.length == 0){
            return RespInfo.error("参数错误");
        }
        List<Integer> list=new ArrayList<>(ids.length);
        for (Integer id : ids) {
            List<Employee> posId = employeeMapper.selectList(new QueryWrapper<Employee>().eq("posId", id));
            if (posId.size()>0){
                for (Employee employee : posId) {
                    employee.setPosId(null);
                }
            }
            list.add(id);
        }
        System.out.println(list);
        positionMapper.deleteBatchIds(list);
        return RespInfo.success("删除职位信息成功");
    }

    @Override
    public RespInfo deleteOnePosition(Integer id) {
        return deleteMorePosition(new Integer[]{id});
    }
}
