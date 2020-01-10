package com.demo.forest.zhkz.system.vo;

import com.demo.forest.zhkz.system.domain.UserInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserInfoVo extends UserInfo {
    private String roleIdName;
}
