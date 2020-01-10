package com.demo.forest.zhkz.data_manage.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.data_manage.dao.PestsDao;
import com.demo.forest.zhkz.data_manage.domain.PestsInfo;
import com.demo.forest.zhkz.data_manage.service.PestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PestsServiceImpl implements PestsService {

    @Autowired
    private PestsDao pestsDao;

    @Autowired
    private MybatisService mybatisService;

    @Override
    public IPage<PestsInfo> queryPestsInfo(Page page, PestsInfo pestsInfo) throws Exception {
        IPage<PestsInfo> pestsInfoIPage = pestsDao.queryPestsInfo(page, pestsInfo);
        return pestsInfoIPage;
    }

    @Override
    public void insertPestsInfo(PestsInfo pestsInfo) throws Exception {
        mybatisService.insert(pestsInfo);
    }

    @Override
    public void updatePestsInfo(PestsInfo pestsInfo) throws Exception {
        PestsInfo condition = new PestsInfo();
        condition.setPestsId(pestsInfo.getPestsId());
        mybatisService.update(pestsInfo, condition);
    }

    @Override
    public void deletePestsInfo(PestsInfo pestsInfo) throws Exception {
        mybatisService.delete(pestsInfo);
    }
}
