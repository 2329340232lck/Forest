package com.demo.forest.zhkz.storeroom.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.storeroom.entity.EquipmentInfo;
import com.demo.forest.zhkz.storeroom.vo.EquipmentInfoVo;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

public interface EquipmentDao {

    IPage<EquipmentInfoVo> queryEquipmentInfo(@Param("page") Page page, @Param("equipmentInfo") EquipmentInfo equipmentInfo) throws SQLException;
}
