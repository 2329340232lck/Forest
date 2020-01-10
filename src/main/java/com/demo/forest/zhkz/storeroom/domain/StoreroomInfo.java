package com.demo.forest.zhkz.storeroom.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@TableName("zhkz_storeroom")
public class StoreroomInfo {
    private BigInteger storeroomId;
    private BigInteger gradeId;
    private BigInteger userId;
    private String storeroomDate;
}
