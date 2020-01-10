package com.demo.forest.zhkz.disaster_control.vo;

import com.demo.forest.zhkz.disaster_control.entity.AreaInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AreaInfoVo extends AreaInfo {
    private String areaTypeName;
    private String gradeName;
}
