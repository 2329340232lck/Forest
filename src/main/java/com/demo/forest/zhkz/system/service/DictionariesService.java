package com.demo.forest.zhkz.system.service;


import com.demo.forest.zhkz.system.domain.DictionariesInfo;

import java.util.List;

public interface DictionariesService {
    List<DictionariesInfo> queryDictionInfoByKey(DictionariesInfo dictionariesInfo) throws Exception;
}
