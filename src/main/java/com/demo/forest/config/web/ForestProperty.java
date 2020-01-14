package com.demo.forest.config.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "forest")
public class ForestProperty {
    /**
     * 排除路径模式
     */
    private String[] excludePaths;
    /**
     * 资源过滤路径
     */
    private String[] resourcePaths;
    /**
     * 权限过滤是否开启
     */
    private Boolean permission = Boolean.FALSE;

    public String[] getExcludePaths() {
        return excludePaths;
    }

    public void setExcludePaths(String[] excludePaths) {
        this.excludePaths = excludePaths;
    }

    public String[] getResourcePaths() {
        return resourcePaths;
    }

    public void setResourcePaths(String[] resourcePaths) {
        this.resourcePaths = resourcePaths;
    }

    public Boolean getPermission() {
        return permission;
    }

    public void setPermission(Boolean permission) {
        this.permission = permission;
    }
}
