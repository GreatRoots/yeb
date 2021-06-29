package com.xxxx.service;

import com.xxxx.pojo.Joblevel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.pojo.Position;
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
public interface IJoblevelService extends IService<Joblevel> {

    List<Joblevel> queryAllJoblevel();

    RespInfo addJoblevel(Joblevel joblevel);

    RespInfo updateJoblevel(Joblevel joblevel);

    RespInfo deleteMoreJoblevel(Integer[] ids);

    RespInfo deleteOneJoblevel(Integer id);
}
