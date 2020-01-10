package com.demo.forest.zhkz.disaster_control.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.exception.custom.ExceptionEnum;
import com.demo.forest.config.exception.custom.HTMLException;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.disaster_control.dao.GradeDao;
import com.demo.forest.zhkz.disaster_control.entity.GradeInfo;
import com.demo.forest.zhkz.disaster_control.service.GradeService;
import com.demo.forest.zhkz.disaster_control.vo.GradeInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private MybatisService mybatisService;

    @Autowired
    private GradeDao gradeDao;

    @Override
    public IPage<GradeInfoVo> queryGradeInfo(Page page, GradeInfoVo gradeInfo) throws Exception {
        return gradeDao.queryGraderInfo(page, gradeInfo);
    }

    @Override
    public void insertGradeInfo(GradeInfo gradeInfo) throws Exception {
        GradeInfo condition = new GradeInfo();
        condition.setGradeName(gradeInfo.getGradeName());
        long count = mybatisService.selectCount(condition);
        if (count > 0) {
            throw new HTMLException(ExceptionEnum.GRADE_REPEAT_INSERT);
        }
        mybatisService.insert(gradeInfo);
    }

    @Override
    public void updateGradeInfo(GradeInfo gradeInfo) throws Exception {
        GradeInfo condition = new GradeInfo();
        condition.setGradeId(gradeInfo.getGradeId());
        mybatisService.update(gradeInfo, condition);
    }

    @Override
    public void deleteGradeInfo(GradeInfo gradeInfo) throws Exception {
        mybatisService.delete(gradeInfo);
    }
}
