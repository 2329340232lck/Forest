package com.demo.forest.zhkz.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@TableName("zhkz_dictionaries")
public class DictionariesInfo {
    private BigInteger dictionariesId;
    private String dictionariesValue;
    private String dictionariesKey;
    private String dictionariesOrder;
    private String dictionariesTable;
}
