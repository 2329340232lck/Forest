package com.demo.forest.zhkz.system.controller;

import com.demo.forest.util.ResponseInfo;
import com.demo.forest.zhkz.system.domain.DictionariesInfo;
import com.demo.forest.zhkz.system.service.DictionariesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.demo.forest.util.ResponseInfo.SUCCESS;

@RestController
@RequestMapping("/dictionaries")
public class DictionariesController {

    @Autowired
    private DictionariesService dictionariesService;

    @RequestMapping("/queryDictionInfoByKey.ajax")
    public ResponseInfo queryDictionInfoByKey(DictionariesInfo dictionariesInfo) throws Exception {
        return SUCCESS(dictionariesService.queryDictionInfoByKey(dictionariesInfo));
    }

}
