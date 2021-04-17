package com.zyh.webstudy.controller.ali;

import com.zyh.webstudy.service.ali.VideoService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: 阿里云视频
 * @author: zyh
 * @date: 2021年04月02日 10:34
 */
@RestController
@RequestMapping("/ali/vedio")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("上传视频")
    @PostMapping("/uploadVedio")
    public ResultUtil uploadVedio(MultipartFile fileVedio){
        if(fileVedio == null){
            return ResultUtil.error("视频文件为空！");
        }
        String fileName = fileVedio.getOriginalFilename();
        String fileTitle = fileName.substring(0,fileName.lastIndexOf("."));
        String videoId  = null;
        try {
            videoId = videoService.uploadVedio(fileName,fileTitle,fileVedio.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultUtil.error("上传视频失败！");
        }
        if(videoId == null){
            return ResultUtil.error("上传视频失败！");
        }else {
            return ResultUtil.success("上传视频成功！",videoId);
        }
    }

    @ApiOperation("删除视频")
    @PostMapping("/deleteVedio/{vedioId}")
    public ResultUtil deleteVedio(@PathVariable String vedioId){
        boolean b = videoService.deleteVedio(vedioId);
        if(b){
            return ResultUtil.success("删除视频成功！");
        }else {
            return ResultUtil.error("删除视频失败！");
        }

    }


    @ApiOperation("获取视频播放凭证")
    @GetMapping("/getVedio/{vedioId}")
    public ResultUtil getVedio(@PathVariable String vedioId){
        String playId = videoService.getVideoPlay(vedioId);
        return ResultUtil.success("获取视频播放凭证成功！",playId);
    }

}
