package com.zyh.webstudy.controller.ali;


import com.zyh.webstudy.service.ali.OssService;
import com.zyh.webstudy.utils.ResultUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/ali/oss")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("上传图片")
        @PostMapping("/uploadImage")
    public ResultUtil uploadOssFileHeader(MultipartFile file){
        if(file == null){
            return ResultUtil.error("文件不存在！");
        }
        // 获取登录后的用户
        String imgUrl = ossService.uploadImage(file);
        if(imgUrl != null){
            return ResultUtil.success("上传头像成功",imgUrl);
        }else {
            return ResultUtil.error("上传头像失败,请稍后尝试");
        }

    }
}
