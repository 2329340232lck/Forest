package com.demo.forest.zhkz.data_manage.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.data_manage.domain.DiseaseInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface DiseaseDao {

    IPage<DiseaseInfo> queryDiseaseInfo(@Param("page") Page page, @Param("diseaseInfo") DiseaseInfo diseaseInfo) throws SQLException;
}
