package com.zyh.webstudy.domain.banner;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 轮播图
 * @author: zyh
 * @date: 2021年05月01日 21:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Banner {
    private Integer id;
    private String imgUrl;
    private String hrefUrl;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
}
