package com.demo.forest.zhkz.disaster_control.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.disaster_control.dao.AreaDao;
import com.demo.forest.zhkz.disaster_control.entity.AreaInfo;
import com.demo.forest.zhkz.disaster_control.service.AreaService;
import com.demo.forest.zhkz.disaster_control.vo.AreaInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private MybatisService mybatisService;

    @Resource
    private AreaDao areaDao;

    @Override
    public IPage<AreaInfoVo> queryAreaInfo(Page page, AreaInfoVo areaInfo) throws Exception {
        return areaDao.queryAreaInfo(page, areaInfo);
    }

    @Override
    public void insertAreaInfo(AreaInfo areaInfo) throws Exception {
        mybatisService.insert(areaInfo);
    }

    @Override
    public void updateAreaInfo(AreaInfo areaInfo) throws Exception {
        AreaInfo condition = new AreaInfo();
        condition.setAreaId(areaInfo.getAreaId());
        mybatisService.update(areaInfo, condition);
    }

    @Override
    public void deleteAreaInfo(AreaInfo AreaInfo) throws Exception {
        mybatisService.delete(AreaInfo);
    }
}
