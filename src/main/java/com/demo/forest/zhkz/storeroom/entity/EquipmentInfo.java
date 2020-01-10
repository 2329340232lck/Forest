package com.demo.forest.zhkz.storeroom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Getter
@Setter
@ToString
@TableName("zhkz_resource")
public class EquipmentInfo {
    private BigInteger resourceId;
    private String resourceName;
    private String resourceType;
    private String resourcePreventType;
    private String resourcePurpose;
}
