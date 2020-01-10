package com.demo.forest.zhkz.data_manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.data_manage.domain.PestsInfo;

public interface PestsService {

    /**
     * 查询虫害信息
     *
     * @param pestsInfo
     * @return
     * @throws Exception
     */
    IPage<PestsInfo> queryPestsInfo(Page page, PestsInfo pestsInfo) throws Exception;

    /**
     * 插入虫害信息.
     *
     * @param pestsInfo
     * @throws Exception
     */
    void insertPestsInfo(PestsInfo pestsInfo) throws Exception;

    /**
     * 更新虫害信息.
     *
     * @param pestsInfo
     * @throws Exception
     */
    void updatePestsInfo(PestsInfo pestsInfo) throws Exception;

    /**
     * 删除虫害信息.
     *
     * @param pestsInfo
     * @throws Exception
     */
    void deletePestsInfo(PestsInfo pestsInfo) throws Exception;

}
