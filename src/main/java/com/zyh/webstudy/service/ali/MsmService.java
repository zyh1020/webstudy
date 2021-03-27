package com.zyh.webstudy.service.ali;

import java.util.Map;

public interface MsmService {
    // 发送验证码
    boolean sendVerificationCode(String phone, Map<String,Object> params);
}
