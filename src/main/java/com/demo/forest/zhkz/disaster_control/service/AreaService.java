package com.demo.forest.zhkz.disaster_control.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.disaster_control.entity.AreaInfo;
import com.demo.forest.zhkz.disaster_control.vo.AreaInfoVo;


public interface AreaService {

    IPage<AreaInfoVo> queryAreaInfo(Page page, AreaInfoVo areaInfo) throws Exception;

    void insertAreaInfo(AreaInfo areaInfo) throws Exception;

    void updateAreaInfo(AreaInfo areaInfo) throws Exception;

    void deleteAreaInfo(AreaInfo areaInfo) throws Exception;

}
