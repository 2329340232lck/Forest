package com.demo.forest.zhkz.disaster_control.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.disaster_control.entity.GradeInfo;
import com.demo.forest.zhkz.disaster_control.vo.GradeInfoVo;


public interface GradeService {

    IPage<GradeInfoVo> queryGradeInfo(Page page, GradeInfoVo gradeInfo) throws Exception;

    void insertGradeInfo(GradeInfo gradeInfo) throws Exception;

    void updateGradeInfo(GradeInfo gradeInfo) throws Exception;

    void deleteGradeInfo(GradeInfo gradeInfo) throws Exception;

}
