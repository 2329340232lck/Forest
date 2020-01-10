package com.demo.forest.zhkz.data_manage.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@TableName(value = "zhkz_mouse")
public class MouseInfo {
    private BigInteger mouseId;
    private String mouseName;
    private String mouseFood;
    private String mouseBreed;
    private String mouseEnemy;
    private String mouseControlPlan;
    private String mouseImg;
    private String mouseHarm;

}
