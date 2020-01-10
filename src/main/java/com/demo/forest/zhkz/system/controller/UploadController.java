package com.demo.forest.zhkz.system.controller;

import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.system.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(value = "/uploadFile.ajax")
    public ResponseInfo uploadFile(HttpServletRequest request, MultipartFile file) throws Exception {
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setResultData(uploadService.uploadFile(request, file));
        return responseInfo;
    }
}
