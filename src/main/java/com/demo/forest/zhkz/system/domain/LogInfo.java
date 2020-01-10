package com.demo.forest.zhkz.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@TableName("zhkz_log")
public class LogInfo {
    private BigInteger logId;
    private String logContent;
    private String logCreateTime;
    private String startDate;
    private String endDate;
}
