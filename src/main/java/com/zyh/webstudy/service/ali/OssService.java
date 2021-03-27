package com.zyh.webstudy.service.ali;


import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    // 上传图片
    String uploadImage(MultipartFile file);
}
