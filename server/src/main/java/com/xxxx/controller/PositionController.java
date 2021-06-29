package com.xxxx.controller;


import com.xxxx.pojo.Position;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IPositionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jlx
 * @since 2021-06-27
 */
@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {

    @Resource
    private IPositionService positionService;

    @GetMapping
    public List<Position> queryAllPosition(){
        return positionService.queryAllPosition();
    }

    @PostMapping
    public RespInfo addPosition(@RequestBody Position position){
        return positionService.addPosition(position);
    }

    @PutMapping
    public RespInfo updatePosition(@RequestBody Position position){
        return positionService.updatePosition(position);
    }

    @DeleteMapping
    public RespInfo deleteMorePosition(Integer[] ids){
        return positionService.deleteMorePosition(ids);
    }

    @DeleteMapping("{id}")
    public RespInfo deleteOnePosition(@PathVariable("id") Integer id){
        return positionService.deleteOnePosition(id);
    }
}
