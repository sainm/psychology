package com.mindvoice.psych.infra.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysRole {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

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

    private List<SysPermission> permissions;
}