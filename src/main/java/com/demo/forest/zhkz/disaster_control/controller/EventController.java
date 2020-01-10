package com.demo.forest.zhkz.disaster_control.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import com.demo.forest.zhkz.disaster_control.service.EventService;
import com.demo.forest.zhkz.disaster_control.vo.EventInfoVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/event")
@RequiresRoles(value = {"超级管理员","灾情管理员"},logical = Logical.OR)
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/queryEventInfo.ajax")
    public ResponseInfo queryEventInfo(Page page, EventInfoVo eventInfoVo) throws Exception {
        return ResponseInfo.SUCCESS(eventService.queryEventInfo(page, eventInfoVo));
    }

    @RequestMapping(value = "/insertEventInfo.ajax")
    public ResponseInfo insertEventInfo(EventInfo eventInfo) throws Exception {
        eventService.insertEventInfo(eventInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/updateEventInfo.ajax")
    public ResponseInfo updateEventInfo(EventInfo eventInfo) throws Exception {
        eventService.updateEventInfo(eventInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/deleteEventInfo.ajax")
    public ResponseInfo deleteEventInfo(EventInfo eventInfo) throws Exception {
        eventService.deleteEventInfo(eventInfo);
        return ResponseInfo.SUCCESS();
    }
}
