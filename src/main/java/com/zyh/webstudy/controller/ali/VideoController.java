package com.zyh.webstudy.controller.ali;

import com.zyh.webstudy.service.ali.VideoService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @PostMapping("/deleteVedio")
    public ResultUtil deleteVedio(){
        return ResultUtil.success("删除视频成功！");
    }


    @ApiOperation("获取视频播放凭证")
    @PostMapping("/getVedio")
    public ResultUtil getVedio(){
        return ResultUtil.success("获取视频播放凭证成功！");
    }

}