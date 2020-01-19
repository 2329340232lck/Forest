package com.demo.forest.zhkz.disaster_control.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.disaster_control.entity.GradeInfo;
import com.demo.forest.zhkz.disaster_control.service.GradeService;
import com.demo.forest.zhkz.disaster_control.vo.GradeInfoVo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.demo.forest.util.ResponseInfo.SUCCESS;

@RestController
@RequiresRoles(value = {"超级管理员", "灾情管理员"}, logical = Logical.OR)
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping(value = "/grade")
    public ResponseInfo queryGradeInfo(Page page, GradeInfoVo gradeInfo) throws Exception {
        return SUCCESS(gradeService.queryGradeInfo(page, gradeInfo));
    }

    @PostMapping(value = "/grade")
    public ResponseInfo insertGradeInfo(GradeInfo gradeInfo) throws Exception {
        gradeService.insertGradeInfo(gradeInfo);
        return SUCCESS();
    }

    @PutMapping(value = "/grade")
    public ResponseInfo updateGradeInfo(GradeInfo gradeInfo) throws Exception {
        gradeService.updateGradeInfo(gradeInfo);
        return SUCCESS();
    }

    @DeleteMapping(value = "/grade")
    public ResponseInfo deleteGradeInfo(GradeInfo gradeInfo) throws Exception {
        gradeService.deleteGradeInfo(gradeInfo);
        return SUCCESS();
    }
}
