package com.demo.forest.zhkz.system.service.impl;

import com.demo.forest.zhkz.system.service.UploadService;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UploadServiceImpl implements UploadService {
    @Override
    public String uploadFile(HttpServletRequest request, MultipartFile file) throws Exception {
        if (file == null || file.isEmpty()) {
            throw new NullPointerException("指定文件为空!");
        }
        String filePath = "D:/zhkzTemp/";
        String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(System.currentTimeMillis()));
        String filename = file.getOriginalFilename();
        Resource resource = file.getResource();
        InputStream inputStream = file.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        return null;
    }
}
