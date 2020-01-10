package com.demo.forest.zhkz.storeroom.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.zhkz.storeroom.domain.StoreroomInfo;
import com.demo.forest.zhkz.storeroom.vo.StorageVo;
import com.demo.forest.zhkz.storeroom.vo.StoreroomInfoVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;

public interface StoreroomDao {

    IPage<StoreroomInfoVo> queryStoreroomInfo(@Param("page") Page page, @Param("storeroomInfo") StoreroomInfoVo storeroomInfo) throws SQLException;

    List<StorageVo> getStoreroom(@Param("storeroomId") String storeroomId) throws SQLException;

    Long insertStoreroomInfo(StoreroomInfo storeroomInfo) throws SQLException;

    @Update("UPDATE zhkz_storeroom SET gradeId = #{gradeId},userId = #{userId},storeroomDate = #{storeroomDate} WHERE (storeroomId=#{storeroomId})")
    Long updateStoreroomInfo(StoreroomInfo storeroomInfo) throws SQLException;
}
