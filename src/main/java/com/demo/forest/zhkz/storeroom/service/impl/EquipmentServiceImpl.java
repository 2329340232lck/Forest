package com.demo.forest.zhkz.storeroom.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.storeroom.dao.EquipmentDao;
import com.demo.forest.zhkz.storeroom.entity.EquipmentInfo;
import com.demo.forest.zhkz.storeroom.service.EquipmentService;
import com.demo.forest.zhkz.storeroom.vo.EquipmentInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private MybatisService mybatisService;

    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public IPage<EquipmentInfoVo> queryEquipmentInfo(Page page, EquipmentInfo equipmentInfo) throws Exception {
        return equipmentDao.queryEquipmentInfo(page, equipmentInfo);
    }

    @Override
    public void insertEquipmentInfo(EquipmentInfo equipmentInfo) throws Exception {
        mybatisService.insert(equipmentInfo);
    }

    @Override
    public void updateEquipmentInfo(EquipmentInfo equipmentInfo) throws Exception {
        EquipmentInfo condition = new EquipmentInfo();
        condition.setResourceId(equipmentInfo.getResourceId());
        mybatisService.update(equipmentInfo, condition);
    }

    @Override
    public void deleteEquipmentInfo(EquipmentInfo equipmentInfo) throws Exception {
        mybatisService.delete(equipmentInfo);
    }
}
