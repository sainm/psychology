package com.mindvoice.psych.infra.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SysPermission {
    /**
     * 主键ID
     */
    private Long id;

    /**
     * 权限标识符（例如：user:list）
     */
    private String permissionCode;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 更新人ID
     */
    private Long updateBy;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
