package com.demo.forest.zhkz.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigInteger;

@TableName(value = "zhkz_user")
@Data
public class UserInfo {
    private BigInteger userId;
    private String userName;
    private String userPassword;
    private String roleId;
    private String userRealName;
}
