package com.demo.forest.zhkz.storeroom.vo;

import com.demo.forest.zhkz.storeroom.domain.StoreroomInfo;
import com.demo.forest.zhkz.storeroom.entity.Storage;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class StoreroomInfoVo extends StoreroomInfo {
    private String gradeName;
    private String userName;
    private String startDate;
    private String endDate;
    private List<Storage> resources;
}
