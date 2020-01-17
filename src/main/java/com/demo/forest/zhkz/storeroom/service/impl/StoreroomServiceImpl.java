package com.demo.forest.zhkz.storeroom.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.forest.config.mybatis.service.MybatisService;
import com.demo.forest.zhkz.storeroom.dao.StoreroomDao;
import com.demo.forest.zhkz.storeroom.domain.StoreroomInfo;
import com.demo.forest.zhkz.storeroom.entity.Storage;
import com.demo.forest.zhkz.storeroom.service.StoreroomService;
import com.demo.forest.zhkz.storeroom.vo.StorageVo;
import com.demo.forest.zhkz.storeroom.vo.StoreroomInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StoreroomServiceImpl implements StoreroomService {

    @Autowired
    private MybatisService mybatisService;

    @Resource
    private StoreroomDao storeroomDao;

    @Override
    public IPage<StoreroomInfoVo> queryStoreroomInfo(Page page, StoreroomInfoVo storeroomInfo) throws Exception {
        return storeroomDao.queryStoreroomInfo(page, storeroomInfo);
    }

    @Override
    public List<StorageVo> getResources(String key) throws Exception {
        return storeroomDao.getStoreroom(key);
    }

    @Override
    @Transactional
    public void insertStoreroomInfo(StoreroomInfoVo storeroomInfoVo) throws Exception {
        List<Storage> storages = storeroomInfoVo.getResources();
        storeroomInfoVo.setStoreroomDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        storeroomDao.insertStoreroomInfo(storeroomInfoVo);
        for (Storage storage : storages) {
            storage.setStoreroomId(storeroomInfoVo.getStoreroomId().toString());
            mybatisService.insert(storage);
        }
    }

    @Override
    @Transactional
    public void updateStoreroomInfo(StoreroomInfoVo storeroomInfo) throws Exception {
        deleteStoreroomInfo(storeroomInfo);
        insertStoreroomInfo(storeroomInfo);
    }

    @Override
    @Transactional
    public void deleteStoreroomInfo(StoreroomInfo storeroomInfo) throws Exception {
        StoreroomInfo condition = new StoreroomInfo();
        condition.setStoreroomId(storeroomInfo.getStoreroomId());
        Storage storage = new Storage();
        storage.setStoreroomId(storeroomInfo.getStoreroomId().toString());
        mybatisService.delete(condition);
        mybatisService.delete(storage);
    }

}
