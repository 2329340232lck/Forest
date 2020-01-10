package com.demo.forest.zhkz.storeroom.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.storeroom.domain.StoreroomInfo;
import com.demo.forest.zhkz.storeroom.vo.StorageVo;
import com.demo.forest.zhkz.storeroom.vo.StoreroomInfoVo;

import java.util.List;


public interface StoreroomService {

    IPage<StoreroomInfoVo> queryStoreroomInfo(Page page, StoreroomInfoVo StoreroomInfo) throws Exception;

    void insertStoreroomInfo(StoreroomInfoVo StoreroomInfo) throws Exception;

    void updateStoreroomInfo(StoreroomInfoVo StoreroomInfo) throws Exception;

    void deleteStoreroomInfo(StoreroomInfo StoreroomInfo) throws Exception;

    List<StorageVo> getResources(String key) throws Exception;
}
