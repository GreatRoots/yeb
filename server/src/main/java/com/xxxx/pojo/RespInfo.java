package com.xxxx.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespInfo {
    private Integer code;
    private String msg;
    private Object obj;
    public static RespInfo success(String msg){
        return new RespInfo(200,msg,null);
    }
    public static RespInfo success(Object obj){
        return new RespInfo(200,null,obj);
    }
    public static RespInfo success(String msg,Object obj){
        return new RespInfo(200,msg,obj);
    }
    public static RespInfo error(String msg){
        return new RespInfo(400,msg,null);
    }
    public static RespInfo error(String msg,Object obj){
        return new RespInfo(400,msg,obj);
    }
}
