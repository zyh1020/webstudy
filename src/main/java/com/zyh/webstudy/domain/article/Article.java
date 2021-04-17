package com.zyh.webstudy.domain.article;

import com.zyh.webstudy.domain.security.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 文章
 * @author: zyh
 * @date: 2021年04月17日 16:51
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Article {
    private Integer id;
    private Integer personId;
    private Integer sortId;
    private Integer lookNum;
    private Boolean isPulish;
    private String articleTitle;
    private String articleContent;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;

    private String sortName;
    private SysUser sysUser;
}
