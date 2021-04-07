package com.zyh.webstudy.vo.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel(description = "用户实体类")
@EqualsAndHashCode(callSuper = false)
public class UserLoginVo {
    @ApiModelProperty(value = "用户名")
    public String username;
    @ApiModelProperty(value = "密码")
    public String password;
}
