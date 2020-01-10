package com.demo.forest.zhkz.expert.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import com.demo.forest.zhkz.disaster_control.vo.EventInfoVo;

public interface DisasterService {

    IPage<EventInfoVo> queryDisasterInfo(Page page, EventInfo eventInfo) throws Exception;
}
