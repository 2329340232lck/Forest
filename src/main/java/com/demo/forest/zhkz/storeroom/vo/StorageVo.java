package com.demo.forest.zhkz.storeroom.vo;

import com.demo.forest.zhkz.storeroom.entity.EquipmentInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StorageVo extends EquipmentInfo {
    private String Id;
    private String resourceTypeName;
    private String resourcePreventType;
    private String resourcePreventTypeName;
    private String storeroomId;
    private Integer resourceNumber;
}
