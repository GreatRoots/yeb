package com.xxxx.service;

import com.xxxx.pojo.Position;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.RespInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
public interface IPositionService extends IService<Position> {

    List<Position> queryAllPosition();

    RespInfo addPosition(Position position);

    RespInfo updatePosition(Position position);

    RespInfo deleteMorePosition(Integer[] ids);

    RespInfo deleteOnePosition(Integer id);
}
