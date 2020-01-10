package com.demo.forest.zhkz.system.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuVo {
    private Integer menuId;
    private String menuName;
    private String isParent;
    private String iconClass;
    private String menuUrl;
    private Integer parentId;
    private Integer menuOrder;
    private List<MenuVo> childMenu;
}
