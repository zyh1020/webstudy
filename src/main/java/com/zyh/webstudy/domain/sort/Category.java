package com.zyh.webstudy.domain.sort;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @Description: 分类
 * @author: zyh
 * @date: 2021年04月06日 18:32
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class Category {
    private Integer id;
    private Integer parentId;
    private String name;
    private Integer level;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
    private List<Category> children;
}
