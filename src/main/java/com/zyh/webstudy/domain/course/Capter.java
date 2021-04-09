package com.zyh.webstudy.domain.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

/**
 * @Description: 章节
 * @author: zyh
 * @date: 2021年04月09日 17:35
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Capter {
    private Integer id;
    private Integer courseId;
    private String title;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
    private List<Vedio> vedios; // 章节下的小节
}
