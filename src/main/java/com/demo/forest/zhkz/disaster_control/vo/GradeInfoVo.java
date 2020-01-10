package com.demo.forest.zhkz.disaster_control.vo;

import com.demo.forest.zhkz.disaster_control.entity.GradeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GradeInfoVo extends GradeInfo {
    private String areaId;
    private String areaName;
}
