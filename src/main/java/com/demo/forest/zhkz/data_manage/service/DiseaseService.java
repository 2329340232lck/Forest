package com.demo.forest.zhkz.data_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.data_manage.domain.DiseaseInfo;


public interface DiseaseService {

    IPage<DiseaseInfo> queryDiseaseInfo(Page page, DiseaseInfo diseaseInfo) throws Exception;

    void insertDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception;

    void updateDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception;

    void deleteDiseaseInfo(DiseaseInfo diseaseInfo) throws Exception;

}
