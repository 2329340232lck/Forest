package com.demo.forest.zhkz.system.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface UploadService {

    String uploadFile(HttpServletRequest request, MultipartFile file) throws Exception;
}
