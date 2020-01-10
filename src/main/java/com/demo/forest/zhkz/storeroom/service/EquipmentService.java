package com.demo.forest.zhkz.storeroom.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.storeroom.entity.EquipmentInfo;
import com.demo.forest.zhkz.storeroom.vo.EquipmentInfoVo;

public interface EquipmentService {

    IPage<EquipmentInfoVo> queryEquipmentInfo(Page page, EquipmentInfo equipmentInfo) throws Exception;

    void insertEquipmentInfo(EquipmentInfo equipmentInfo) throws Exception;

    void updateEquipmentInfo(EquipmentInfo equipmentInfo) throws Exception;

    void deleteEquipmentInfo(EquipmentInfo equipmentInfo) throws Exception;
}
