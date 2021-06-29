package com.xxxx.controller;


import com.xxxx.pojo.Joblevel;
import com.xxxx.pojo.Position;
import com.xxxx.pojo.RespInfo;
import com.xxxx.service.IJoblevelService;
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
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {

    @Resource
    private IJoblevelService joblevelService;

    @GetMapping
    public List<Joblevel> queryAllJoblevel(){
        return joblevelService.queryAllJoblevel();
    }

    @PostMapping
    public RespInfo addJoblevel(@RequestBody Joblevel joblevel){
        return joblevelService.addJoblevel(joblevel);
    }

    @PutMapping
    public RespInfo updateJoblevel(@RequestBody Joblevel joblevel){
        return joblevelService.updateJoblevel(joblevel);
    }

    @DeleteMapping
    public RespInfo deleteMoreJoblevel(Integer[] ids){
        return joblevelService.deleteMoreJoblevel(ids);
    }

    @DeleteMapping("{id}")
    public RespInfo deleteOneJoblevel(@PathVariable("id") Integer id){
        return joblevelService.deleteOneJoblevel(id);
    }
}
