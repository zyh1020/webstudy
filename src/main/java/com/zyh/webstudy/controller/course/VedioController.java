package com.zyh.webstudy.controller.course;
import com.zyh.webstudy.domain.course.Vedio;
import com.zyh.webstudy.service.course.VedioService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 小节和视频
 * @author: zyh
 * @date: 2021年04月09日 18:26
 */
@RestController
@RequestMapping("/cou/vedio")
public class VedioController {

    @Autowired
    private VedioService vedioService;

    @ApiOperation("添加小节")
    @PostMapping("/addVedio")
    public ResultUtil addVedio(@RequestBody Vedio vedio){
        Integer capterId = vedioService.addVedio(vedio);
        return ResultUtil.success("添加章节成功！",capterId);
    }
}
