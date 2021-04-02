package com.zyh.webstudy.service.ali;
import java.io.InputStream;

public interface VideoService {
    String uploadVedio(String fileName, String fileTitle, InputStream inputStream);
    String deleteVedio(String vedioIds);
}
