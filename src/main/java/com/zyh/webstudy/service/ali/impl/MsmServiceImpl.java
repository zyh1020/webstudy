package com.zyh.webstudy.service.ali.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zyh.webstudy.service.ali.MsmService;
import com.zyh.webstudy.utils.AccessPropertiesUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {
    @Override
    public boolean sendVerificationCode(String phone, Map<String,Object> params) {

        // 配置信息
        DefaultProfile profile = DefaultProfile.getProfile(AccessPropertiesUtil.REGION_ID, AccessPropertiesUtil.ACCESS_KEY_ID, AccessPropertiesUtil.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        // 固定配置
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");


        // 设置发送的短信模板
        request.putQueryParameter("PhoneNumbers",phone); // 手机号
        request.putQueryParameter("SignName", AccessPropertiesUtil.SIGN_NAME);
        request.putQueryParameter("TemplateCode", AccessPropertiesUtil.TEMPLATE_CODE);

        // 设置发送的短信内容，内容必须是JSON格式的字符串
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(params));

        // 最终发送
        try {
            CommonResponse response = client.getCommonResponse(request);
            return response.getHttpResponse().isSuccess(); // 返回是否发送成功
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
