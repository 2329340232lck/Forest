package com.demo.forest.zhkz.disaster_control.vo;

import com.demo.forest.zhkz.disaster_control.entity.EventInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventInfoVo extends EventInfo {
    private String areaName;
    private String startDate;
    private String endDate;
    private String eventDisasterStageName;
}
