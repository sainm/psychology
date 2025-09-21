package com.mindvoice.psych.infra.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SysUser {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户状态：0-禁用, 1-启用
     */
    private Integer status;

    /**
     * 第三方登录ID
     */
    private String providerId;

    /**
     * 第三方登录类型（google, github等）
     */
    private String providerType;

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

    private List<SysRole> roles;
}
