package com.demo.forest.zhkz.disaster_control.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.disaster_control.entity.GradeInfo;
import com.demo.forest.zhkz.disaster_control.service.GradeService;
import com.demo.forest.zhkz.disaster_control.vo.GradeInfoVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grade")
@RequiresRoles(value = {"超级管理员", "灾情管理员"}, logical = Logical.OR)
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "/queryGradeInfo.ajax")
    public ResponseInfo queryGradeInfo(Page page, GradeInfoVo gradeInfo) throws Exception {
        return ResponseInfo.SUCCESS(gradeService.queryGradeInfo(page, gradeInfo));
    }

    @RequestMapping(value = "/insertGradeInfo.ajax")
    public ResponseInfo insertGradeInfo(GradeInfo gradeInfo) throws Exception {
        gradeService.insertGradeInfo(gradeInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/updateGradeInfo.ajax")
    public ResponseInfo updateGradeInfo(GradeInfo gradeInfo) throws Exception {
        gradeService.updateGradeInfo(gradeInfo);
        return ResponseInfo.SUCCESS();
    }

    @RequestMapping(value = "/deleteGradeInfo.ajax")
    public ResponseInfo deleteGradeInfo(GradeInfo gradeInfo) throws Exception {
        gradeService.deleteGradeInfo(gradeInfo);
        return ResponseInfo.SUCCESS();
    }
}
