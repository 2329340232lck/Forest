package com.demo.forest.zhkz.data_manage.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.data_manage.domain.MouseInfo;
import org.apache.ibatis.annotations.Param;

public interface MouseDao {

    IPage<MouseInfo> queryMouseInfo(@Param("page") Page page, @Param("mouseInfo") MouseInfo mouseInfo);

}
