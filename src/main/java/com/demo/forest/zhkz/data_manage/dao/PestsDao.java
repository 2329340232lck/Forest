package com.demo.forest.zhkz.data_manage.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.data_manage.domain.PestsInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PestsDao {

    IPage<PestsInfo> queryPestsInfo(@Param("page") Page page, @Param("pestsInfo") PestsInfo pestsInfo);
}
