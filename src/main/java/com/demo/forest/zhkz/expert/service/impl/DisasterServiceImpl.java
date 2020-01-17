package com.demo.forest.zhkz.expert.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import com.demo.forest.zhkz.disaster_control.vo.EventInfoVo;
import com.demo.forest.zhkz.expert.dao.DisasterDao;
import com.demo.forest.zhkz.expert.service.DisasterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DisasterServiceImpl implements DisasterService {

    @Resource
    private DisasterDao disasterDao;

    @Override
    public IPage<EventInfoVo> queryDisasterInfo(Page page, EventInfo eventInfo) throws Exception {
        return disasterDao.queryDisasterInfo(page,eventInfo);
    }
}
