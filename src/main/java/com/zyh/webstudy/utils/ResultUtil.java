package com.zyh.webstudy.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* 统一返回结果
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultUtil {
    // 状态
    private Boolean success;
    // 状态码
    private Integer code;
    // 信息
    private String message;
    // 结果集
    private Object data;

    // 成功静态方法
    public static ResultUtil success(String message) {
        return new ResultUtil(true,200,message,null);
    }
    public static ResultUtil success(String message,Object data) {
        return new ResultUtil(true,200,message,data);
    }
    // 失败的静态方法
    public static ResultUtil error(String message) {
        return new ResultUtil(false,401,message,null);
    }

    public static ResultUtil error(Integer code,String message) {
        return new ResultUtil(false,code,message,null);
    }
    public static ResultUtil error(String message,Object data) {
        return new ResultUtil(false,401,message,data);
    }
    public static ResultUtil error(Integer code,String message,Object data) {
        return new ResultUtil(false,code,message,data);
    }


}
