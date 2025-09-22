package com.mindvoice.psych.infra.domain;

import com.mindvoice.psych.common.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysUser extends BaseDO {

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


    private List<SysRole> roles;
}
