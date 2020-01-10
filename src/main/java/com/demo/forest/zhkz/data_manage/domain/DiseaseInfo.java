package com.demo.forest.zhkz.data_manage.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;

@TableName(value = "zhkz_disease")
@Data
public class DiseaseInfo {
    private BigInteger diseaseId;
    private String diseaseName;
    private String diseaseEtiology;
    private String diseaseSymptom;
    private String diseasePeriod;
    private String diseaseHarm;
    private String diseaseControlPlan;
    private String diseaseImg;
}
