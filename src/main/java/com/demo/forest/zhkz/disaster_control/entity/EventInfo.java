package com.demo.forest.zhkz.disaster_control.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@TableName("zhkz_event")
public class EventInfo {
    private BigInteger eventId;
    private String eventName;
    private String eventStartTime;
    private String eventDisasterStage;
    private String eventDisasterType;
    private String eventLoss;
    private String eventExpertAdvice;
    private String eventImg;
    private String eventDiscoverer;
    private String eventInfluenceArea;
    private String eventControlPlan;
    private String eventRemark;
    private String areaId;
}
