package com.demo.forest.zhkz.data_manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.data_manage.dao.DiseaseDao;
import com.demo.forest.zhkz.data_manage.domain.DiseaseInfo;
import com.demo.forest.zhkz.data_manage.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private MybatisService mybatisService;

    @Resource
    private DiseaseDao diseaseDao;

    @Override
    public IPage<DiseaseInfo> queryDiseaseInfo(Page page, DiseaseInfo diseaseInfo) throws Exception {
        return diseaseDao.queryDiseaseInfo(page, diseaseInfo);
    }

    @Override
    public void insertDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception {
        mybatisService.insert(diseaseInfo);
    }

    @Override
    public void updateDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception {
        DiseaseInfo condition = new DiseaseInfo();
        condition.setDiseaseId(diseaseInfo.getDiseaseId());
        mybatisService.update(diseaseInfo, condition);
    }

    @Override
    public void deleteDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception {
        mybatisService.delete(diseaseInfo);
    }
}
