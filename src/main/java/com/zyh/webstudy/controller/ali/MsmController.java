package com.zyh.webstudy.controller.ali;

import com.zyh.webstudy.service.ali.MsmService;
import com.zyh.webstudy.utils.CommonUtil;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ali/msm")
public class MsmController {
    @Autowired
    private MsmService msmService;

    @ApiOperation("发送验证码")
    @GetMapping("/sendCode/{phone}")
    public ResultUtil uploadOssFileHeader(@PathVariable String phone, HttpServletRequest request){
        if(phone == null){
            return ResultUtil.error("号码不能为空！");
        }
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(300);
        String code = (String)session.getAttribute(phone);
        Map<String,Object> params = new HashMap<>();
        if(code == null){
            // 获取随机数
            code = CommonUtil.getFourBitRandom();
            params.put("code",code);
        }else{
            params.put("code",code); // 键必须是code，为了和模板数据保持一致
        }
        // 获取登录后的用户
        boolean isOk = msmService.sendVerificationCode(phone,params);
        if(isOk){
            session.setAttribute(phone,code); // 存储
            return ResultUtil.success("验证码发送成功，请注意查收");
        }else {
            return ResultUtil.error("验证码发送失败,请稍后尝试");
        }

    }
}
