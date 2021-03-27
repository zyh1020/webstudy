package com.zyh.webstudy.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/*
*  阿里云对象的存储的配置信息读取,
*  InitializingBean: spring加载之后执行的方法
* */
@Component
public class OssPropertiesUtil implements InitializingBean {

    @Value("${aliyun.oss.file.endPoint}")
    private String endPoint; // 地域
    @Value("${aliyun.oss.file.accessKeyId}")
    private String accessKeyId; //
    @Value("${aliyun.oss.file.accessKeySecret}")
    private String accessKeySecret; //
    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName; // bucket名称

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endPoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
    }
}
