package com.demo.forest.zhkz.system.service.impl;

import com.demo.forest.zhkz.system.dao.DictionariesDao;
import com.demo.forest.zhkz.system.domain.DictionariesInfo;
import com.demo.forest.zhkz.system.service.DictionariesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DictionariesServiceImpl implements DictionariesService {

    @Resource
    private DictionariesDao dictionariesDao;

    @Override
    public List<DictionariesInfo> queryDictionInfoByKey(DictionariesInfo dictionariesInfo) throws Exception {
        List<DictionariesInfo> dictionariesInfos = dictionariesDao.queryDictionInfoByKey(dictionariesInfo);
        return dictionariesInfos;
    }
}
