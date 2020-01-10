package com.demo.forest.zhkz.data_manage.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;

@TableName(value = "zhkz_pests")
@Data
public class PestsInfo {
    private BigInteger pestsId;
    private String pestsName;
    private String pestsHost;
    private String pestsBreed;
    private String pestsEnemy;
    private String pestsHarm;
    private String pestsControlPlan;
    private String pestsLarvaImg;
    private String pestsAdultImg;
}
