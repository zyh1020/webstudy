package com.zyh.webstudy.controller.ali;

import com.zyh.webstudy.service.ali.MsmService;
import com.zyh.webstudy.utils.CommonUtil;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ali/msm")
public class MsmController {
    @Autowired
    private MsmService msmService;

    @ApiOperation("发送验证码")
    @GetMapping("/sendCode")
    public ResultUtil uploadOssFileHeader(String phone){
        if(phone == null){
            return ResultUtil.error("号码不能为空！");
        }
        Map<String,Object> params = new HashMap<>();
        // 获取随机数
        String code = CommonUtil.getFourBitRandom();
        params.put("code",code); // 键必须是code，为了和模板数据保持一致

        // 获取登录后的用户
        boolean isOk = msmService.sendVerificationCode(phone,params);

        if(isOk){
            return ResultUtil.success("验证码发送成功，请注意查收");
        }else {
            return ResultUtil.error("验证码发送失败,请稍后尝试");
        }

    }
}
