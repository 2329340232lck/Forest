package com.demo.forest.zhkz.data_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.data_manage.domain.MouseInfo;


public interface MouseService {

    IPage<MouseInfo> queryMouseInfo(Page page, MouseInfo mouseInfo) throws Exception;

    void insertMouseInfo(MouseInfo mouseInfo) throws Exception;

    void updateMouseInfo(MouseInfo mouseInfo) throws Exception;

    void deleteMouseInfo(MouseInfo mouseInfo)throws Exception;
}
