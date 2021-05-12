package com.zyh.webstudy.controller.course;
import com.zyh.webstudy.domain.course.Vedio;
import com.zyh.webstudy.service.course.VedioService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 小节和视频
 * @author: zyh
 * @date: 2021年04月09日 18:26
 */
@RestController
@RequestMapping("/cou/vedio")
public class SectionController {

    @Autowired
    private VedioService vedioService;

    @ApiOperation("添加小节")
    @PostMapping("/addVedio")
    public ResultUtil addVedio(@RequestBody Vedio vedio){
        Integer capterId = vedioService.addVedio(vedio);
        return ResultUtil.success("添加章节成功！",capterId);
    }


    @ApiOperation("修改小节")
    @PostMapping("/updateVedio")
    public ResultUtil updateVedio(@RequestBody Vedio vedio){
        vedioService.updateVedio(vedio);
        return ResultUtil.success("修改小节成功！");
    }


    @ApiOperation("删除小节")
    @GetMapping("/deleteOneVedio/{vedioId}")
    public ResultUtil deleteOneVedio(@PathVariable Integer vedioId){
        vedioService.deleteOneVedio(vedioId);
        return ResultUtil.success("删除小节成功！");
    }
}
