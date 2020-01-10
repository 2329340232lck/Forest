package com.demo.forest.zhkz.disaster_control.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@TableName("zhkz_area")
public class AreaInfo {
    private String areaId;
    private String areaName;
    private String areaForestType;
    private String areaType;
    private String areaDominantSpecies;
}
