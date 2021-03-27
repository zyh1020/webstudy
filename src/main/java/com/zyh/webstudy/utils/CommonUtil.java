package com.zyh.webstudy.utils;

import java.util.UUID;

public class CommonUtil {

    // 获取唯一id
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
