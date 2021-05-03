package com.zyh.webstudy.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 登录
 * @author: zyh
 * @date: 2021年05月01日 15:19
 */
@Data
@ApiModel(description = "登录实体类")
@EqualsAndHashCode(callSuper = false)
public class UserLoginVo {
    @ApiModelProperty(value = "用户名")
    public String username;
    @ApiModelProperty(value = "密码")
    public String password;
    @ApiModelProperty(value = "验证码")
    public String code;

}
