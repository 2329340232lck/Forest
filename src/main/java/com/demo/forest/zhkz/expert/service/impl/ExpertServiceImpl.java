package com.demo.forest.zhkz.expert.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.expert.dao.ExpertDao;
import com.demo.forest.zhkz.expert.entity.ExpertInfo;
import com.demo.forest.zhkz.expert.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private MybatisService mybatisService;

    @Resource
    private ExpertDao expertDao;

    @Override
    public IPage<ExpertInfo> queryExpertInfo(Page page, ExpertInfo expertInfo) throws Exception {
        return expertDao.queryExpertInfo(page, expertInfo);
    }

    @Override
    public void insertExpertInfo(ExpertInfo expertInfo) throws Exception {
        mybatisService.insert(expertInfo);
    }

    @Override
    public void updateExpertInfo(ExpertInfo expertInfo) throws Exception {
        ExpertInfo condition = new ExpertInfo();
        condition.setExpertId(expertInfo.getExpertId());
        mybatisService.update(expertInfo, condition);
    }

    @Override
    public void deleteExpertInfo(ExpertInfo expertInfo) throws Exception {
        mybatisService.delete(expertInfo);
    }
}
