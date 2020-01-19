package com.demo.forest.zhkz.disaster_control.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import com.demo.forest.zhkz.disaster_control.service.EventService;
import com.demo.forest.zhkz.disaster_control.vo.EventInfoVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.demo.forest.util.ResponseInfo.SUCCESS;

@RestController
@RequiresRoles(value = {"超级管理员","灾情管理员"},logical = Logical.OR)
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/event")
    public ResponseInfo queryEventInfo(Page page, EventInfoVo eventInfoVo) throws Exception {
        return SUCCESS(eventService.queryEventInfo(page, eventInfoVo));
    }

    @PostMapping(value = "/event")
    public ResponseInfo insertEventInfo(EventInfo eventInfo) throws Exception {
        eventService.insertEventInfo(eventInfo);
        return SUCCESS();
    }

    @PutMapping(value = "/event")
    public ResponseInfo updateEventInfo(EventInfo eventInfo) throws Exception {
        eventService.updateEventInfo(eventInfo);
        return SUCCESS();
    }

    @DeleteMapping(value = "/event")
    public ResponseInfo deleteEventInfo(EventInfo eventInfo) throws Exception {
        eventService.deleteEventInfo(eventInfo);
        return SUCCESS();
    }
}
