package com.demo.forest.zhkz.storeroom.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("zhkz_storage")
public class Storage {
    private String id;
    private String resourceId;
    private String storeroomId;
    private String resourceNumber;
}
