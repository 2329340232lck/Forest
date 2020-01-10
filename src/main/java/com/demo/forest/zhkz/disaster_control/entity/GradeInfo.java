package com.demo.forest.zhkz.disaster_control.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@TableName("zhkz_grade")
public class GradeInfo {
    private BigInteger gradeId;
    private String gradeName;
    private String gradeCreationDate;
    private String gradeLeader;
    private String gradeNumber;
    private String gradeLeaderPhone;
    private String areaId;
}
