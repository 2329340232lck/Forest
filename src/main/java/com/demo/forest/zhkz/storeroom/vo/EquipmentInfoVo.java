package com.demo.forest.zhkz.storeroom.vo;

import com.demo.forest.zhkz.storeroom.entity.EquipmentInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EquipmentInfoVo extends EquipmentInfo {
    private String resourceTypeName;
    private String resourcePreventTypeName;
}
