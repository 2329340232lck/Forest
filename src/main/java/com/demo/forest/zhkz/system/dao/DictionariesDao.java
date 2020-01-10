package com.demo.forest.zhkz.system.dao;


import com.demo.forest.zhkz.system.domain.DictionariesInfo;

import java.sql.SQLException;
import java.util.List;

public interface DictionariesDao {

    List<DictionariesInfo> queryDictionInfoByKey(DictionariesInfo dictionariesInfo) throws SQLException;
}
