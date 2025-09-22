package com.mindvoice.psych.infra.domain;

import com.mindvoice.psych.common.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysPermission extends BaseDO {
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

}
