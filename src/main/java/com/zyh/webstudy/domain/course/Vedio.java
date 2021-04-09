package com.zyh.webstudy.domain.course;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @Description: 小节视频
 * @author: zyh
 * @date: 2021年04月09日 17:37
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Vedio {
    private Integer id;
    private Integer courseId;
    private Integer capterId;
    private Integer vedioId;
    private String title;
    private Boolean delete;
    private Date createTime;
    private Date updateTime;
}
