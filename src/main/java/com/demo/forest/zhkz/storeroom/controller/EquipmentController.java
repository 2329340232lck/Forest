package com.demo.forest.zhkz.storeroom.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.storeroom.entity.EquipmentInfo;
import com.demo.forest.zhkz.storeroom.service.EquipmentService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.demo.forest.util.ResponseInfo.SUCCESS;

@RestController
@RequiresRoles(value = {"超级管理员", "库房管理员"}, logical = Logical.OR)
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping(value = "/equipment")
    public ResponseInfo queryEquipmentInfo(Page page, EquipmentInfo equipmentInfo) throws Exception {
        return SUCCESS(equipmentService.queryEquipmentInfo(page, equipmentInfo));
    }

    @PostMapping(value = "/equipment")
    public ResponseInfo insertEquipmentInfo(EquipmentInfo equipmentInfo) throws Exception {
        equipmentService.insertEquipmentInfo(equipmentInfo);
        return SUCCESS();
    }

    @PutMapping(value = "/equipment")
    public ResponseInfo updateEquipmentInfo(EquipmentInfo equipmentInfo) throws Exception {
        equipmentService.updateEquipmentInfo(equipmentInfo);
        return SUCCESS();
    }

    @DeleteMapping(value = "/equipment")
    public ResponseInfo deleteEquipmentInfo(EquipmentInfo equipmentInfo) throws Exception {
        equipmentService.deleteEquipmentInfo(equipmentInfo);
        return SUCCESS();
    }
}
