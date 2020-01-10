package com.demo.forest.zhkz.disaster_control.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.disaster_control.dao.EventDao;
import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import com.demo.forest.zhkz.disaster_control.service.EventService;
import com.demo.forest.zhkz.disaster_control.vo.EventInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private MybatisService mybatisService;

    @Autowired
    private EventDao eventDao;

    @Override
    public IPage<EventInfoVo> queryEventInfo(Page page, EventInfoVo eventInfoVo) throws Exception {
        return eventDao.queryEventInfo(page,eventInfoVo);
    }

    @Override
    public void insertEventInfo(EventInfo eventInfo) throws Exception {
        mybatisService.insert(eventInfo);
    }

    @Override
    public void updateEventInfo(EventInfo eventInfo) throws Exception {
        EventInfo condition = new EventInfo();
        condition.setEventId(eventInfo.getEventId());
        mybatisService.update(eventInfo, condition);
    }

    @Override
    public void deleteEventInfo(EventInfo eventInfo) throws Exception {
        mybatisService.delete(eventInfo);
    }
}
