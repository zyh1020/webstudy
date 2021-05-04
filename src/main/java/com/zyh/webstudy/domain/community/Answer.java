package com.zyh.webstudy.domain.community;

import com.zyh.webstudy.domain.security.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 答案
 * @author: zyh
 * @date: 2021年04月16日 22:09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Answer{
    private Integer id;
    private Integer personId;
    private Integer problemId;
    private String answerContent;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;

    private SysUser sysUser;
    private Problem problem;
}
