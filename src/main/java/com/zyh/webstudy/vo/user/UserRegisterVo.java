package com.zyh.webstudy.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description: 用户
 * @author: zyh
 * @date: 2021年05月04日 21:56
 */
@Data
@ApiModel(description = "登录实体类")
@EqualsAndHashCode(callSuper = false)
public class UserRegisterVo {
    @ApiModelProperty(value = "手机/用户名")
    public String phone;
    @ApiModelProperty(value = "密码")
    public String password;
    @ApiModelProperty(value = "昵称")
    public String name;
    @ApiModelProperty(value = "头像")
    private String userAvatar;
    @ApiModelProperty(value = "验证码")
    public String code;
}
