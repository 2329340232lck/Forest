package com.demo.forest.zhkz.expert.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.expert.entity.ExpertInfo;


public interface ExpertService {

    IPage<ExpertInfo> queryExpertInfo(Page page, ExpertInfo ExpertInfo) throws Exception;

    void insertExpertInfo(ExpertInfo ExpertInfo) throws Exception;

    void updateExpertInfo(ExpertInfo ExpertInfo) throws Exception;

    void deleteExpertInfo(ExpertInfo ExpertInfo) throws Exception;
}
