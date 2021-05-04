package com.zyh.webstudy.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @author: zyh
 * @date: 2021年05月04日 22:01
 */
@Data
@ApiModel(description = "登录实体类")
@EqualsAndHashCode(callSuper = false)
public class UserUpdateVo {
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "头像")
    private String userAvatar;
    @ApiModelProperty(value = "昵称")
    private String name;
}
