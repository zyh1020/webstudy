package com.zyh.webstudy.service.ali.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zyh.webstudy.service.ali.OssService;
import com.zyh.webstudy.utils.CommonUtil;
import com.zyh.webstudy.utils.AccessPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class OssServiceImpl implements OssService {

    /* 上传图片 */
    @Override
    public String uploadImage(MultipartFile file) {

        // ①，获取配置
        String endpoint = AccessPropertiesUtil.END_POINT;
        String accessKeyId =  AccessPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = AccessPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = AccessPropertiesUtil.BUCKET_NAME;

        String filePath = new DateTime().toString("yyyy/MM/dd");
        String fileName = filePath + "/" +CommonUtil.getUUID()+"-"+file.getOriginalFilename();
        // ②，上传图片
        try {
            // 创建OSSClient实例。
            OSS ossClient = null;
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();
            // 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // ③，拼接图片链接 例如：https://webstudy.oss-cn-beijing.aliyuncs.com/header.png
        String imgUrl = "https://" + bucketName + "." + endpoint + "/" + fileName;
        return imgUrl;
    }
}
