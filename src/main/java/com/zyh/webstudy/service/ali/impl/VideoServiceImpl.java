package com.zyh.webstudy.service.ali.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.zyh.webstudy.service.ali.VideoService;
import com.zyh.webstudy.utils.AccessPropertiesUtil;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @Description: 阿里云视频播放
 * @author: zyh
 * @date: 2021年04月02日 10:41
 */
@Service
public class VideoServiceImpl implements VideoService {


    /**
      *@Description: 上传视频
      *@Param: [fileName, fileTitle, inputStream]
      *@return: java.lang.String 
      *@Author: zyh
      *@Date: 2021/4/2 14:44
     **/
    @Override
    public String uploadVedio(String fileName, String fileTitle, InputStream inputStream) {
        // 创建请求
        UploadStreamRequest request = new UploadStreamRequest(AccessPropertiesUtil.ACCESS_KEY_ID, AccessPropertiesUtil.ACCESS_KEY_SECRET, fileTitle, fileName, inputStream);
        // 创建请求实现
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        // 解析响应
        String vedioId = null;
        if (response.isSuccess()) {
            vedioId = response.getVideoId();
        }else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            vedioId = response.getVideoId();
        }
        return vedioId;
    }

    /**
      *@Description: 删除视频
      *@Param: [vedioIds]
      *@return: java.lang.String 
      *@Author: zyh
      *@Date: 2021/4/2 14:45
     **/
    @Override
    public boolean deleteVedio(String vedioIds) {
        // 初始化
        DefaultAcsClient client  = initVodClient();
        // ①，创建请求和响应
        DeleteVideoRequest request = new DeleteVideoRequest();
        DeleteVideoResponse response = new DeleteVideoResponse();
        // ②，封装参数
        request.setVideoIds(vedioIds);//支持传入多个视频ID，多个用逗号分隔
        // ③，发送请求
        try {
            response = client.getAcsResponse(request);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
      *@Description: 获取视频播放凭证
      *@Param: [vedioId]
      *@return: java.lang.String 
      *@Author: zyh
      *@Date: 2021/4/5 16:28
     **/
    @Override
    public String getVideoPlay(String vedioId) {
        // 初始化
        DefaultAcsClient client  = initVodClient();
        // ①，创建请求和响应
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        // ②，封装参数
        request.setVideoId(vedioId);
        // ③，发送请求
        try {
            response = client.getAcsResponse(request);
            // ④，解析请求
            return response.getPlayAuth();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    // 初始化
    private DefaultAcsClient initVodClient() throws ClientException {
        DefaultProfile profile = DefaultProfile.getProfile(AccessPropertiesUtil.VIDEO_REGION_ID, AccessPropertiesUtil.ACCESS_KEY_ID, AccessPropertiesUtil.ACCESS_KEY_SECRET);
        return new DefaultAcsClient(profile);
    }
}
