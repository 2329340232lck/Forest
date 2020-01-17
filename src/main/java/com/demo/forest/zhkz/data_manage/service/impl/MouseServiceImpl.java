package com.demo.forest.zhkz.data_manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.data_manage.dao.MouseDao;
import com.demo.forest.zhkz.data_manage.domain.MouseInfo;
import com.demo.forest.zhkz.data_manage.service.MouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class MouseServiceImpl implements MouseService {

    @Autowired
    private MybatisService mybatisService;

    @Resource
    private MouseDao mouseDao;

    @Override
    public IPage<MouseInfo> queryMouseInfo(Page page, MouseInfo mouseInfo) throws Exception {
        return mouseDao.queryMouseInfo(page, mouseInfo);
    }

    @Override
    public void insertMouseInfo(MouseInfo mouseInfo) throws Exception {
        mybatisService.insert(mouseInfo);
    }

    @Override
    public void updateMouseInfo(MouseInfo mouseInfo) throws Exception {
        MouseInfo condition = new MouseInfo();
        condition.setMouseId(mouseInfo.getMouseId());
        mybatisService.update(mouseInfo,condition);
    }

    @Override
    public void deleteMouseInfo(MouseInfo mouseInfo) throws Exception {
        mybatisService.delete(mouseInfo);
    }
}
