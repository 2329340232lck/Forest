package com.demo.forest.zhkz.disaster_control.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import com.demo.forest.zhkz.disaster_control.vo.EventInfoVo;


public interface EventService {

    IPage<EventInfoVo> queryEventInfo(Page page, EventInfoVo eventInfoVo) throws Exception;

    void insertEventInfo(EventInfo eventInfo) throws Exception;

    void updateEventInfo(EventInfo eventInfo) throws Exception;

    void deleteEventInfo(EventInfo eventInfo) throws Exception;
}
