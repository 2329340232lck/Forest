package com.demo.forest.zhkz.expert.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@TableName("zhkz_expert")
public class ExpertInfo {
    private BigInteger expertId;
    private String expertName;
    private String expertBirthDay;
    private String expertSex;
    private String expertSpeciality;
    private String expertPhoneNumber;
    private String expertAddress;
    private String expertImg;
    private String expertProfession;
    private String expertCompany;
    private String expertEmali;

}
