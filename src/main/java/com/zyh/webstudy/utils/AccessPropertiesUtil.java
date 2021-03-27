package com.zyh.webstudy.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/*
*  阿里云对象的存储的配置信息读取,
*  InitializingBean: spring加载之后执行的方法
* */
@Component
public class AccessPropertiesUtil implements InitializingBean {

    @Value("${aliyun.accessKeyId}")
    private String accessKeyId; // 密钥id
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret; // 密钥值

    @Value("${aliyun.oss.endPoint}")
    private String endPoint; // oss的地域
    @Value("${aliyun.oss.bucketName}")
    private String bucketName; // oss的bucket名称

    @Value("${aliyun.msm.regionId}")
    private String regionId; // 短信发送来源

    @Value("${aliyun.msm.signName}")
    private String signName; // 短信签名

    @Value("${aliyun.msm.templateCode}")
    private String templateCode; // 短信模板

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    public static String REGION_ID;
    public static String SIGN_NAME;
    public static String TEMPLATE_CODE;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endPoint;
        ACCESS_KEY_ID = accessKeyId;
        ACCESS_KEY_SECRET = accessKeySecret;
        BUCKET_NAME = bucketName;
        REGION_ID = regionId;
        SIGN_NAME = signName;
        TEMPLATE_CODE = templateCode;
    }
}
