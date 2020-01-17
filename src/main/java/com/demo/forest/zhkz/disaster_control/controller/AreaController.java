package com.demo.forest.zhkz.disaster_control.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.disaster_control.entity.AreaInfo;
import com.demo.forest.zhkz.disaster_control.service.AreaService;
import com.demo.forest.zhkz.disaster_control.vo.AreaInfoVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiresRoles(value = {"超级管理员","灾情管理员"},logical = Logical.OR)
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping(value = "/area")
    public ResponseInfo queryAreaInfo(Page page, AreaInfoVo areaInfo) throws Exception {
        return ResponseInfo.SUCCESS(areaService.queryAreaInfo(page, areaInfo));
    }

    @PostMapping(value = "/area")
    public ResponseInfo insertAreaInfo(AreaInfo areaInfo) throws Exception {
        areaService.insertAreaInfo(areaInfo);
        return ResponseInfo.SUCCESS();
    }

    @PutMapping(value = "/area")
    public ResponseInfo updateAreaInfo(AreaInfo areaInfo) throws Exception {
        areaService.updateAreaInfo(areaInfo);
        return ResponseInfo.SUCCESS();
    }

    @DeleteMapping(value = "/area")
    public ResponseInfo deleteAreaInfo(AreaInfo areaInfo) throws Exception {
        areaService.deleteAreaInfo(areaInfo);
        return ResponseInfo.SUCCESS();
    }
}
