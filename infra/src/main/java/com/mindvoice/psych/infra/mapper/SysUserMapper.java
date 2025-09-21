package com.mindvoice.psych.infra.mapper;

import com.mindvoice.psych.infra.domain.SysPermission;
import com.mindvoice.psych.infra.domain.SysRole;
import com.mindvoice.psych.infra.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    SysUser findByUsername(@Param("username") String username);

    List<SysRole> findRolesByUserId(Long userId);

    /**
     * 根据角色ID查询权限列表
     * @param roleId 角色ID
     * @return 该角色拥有的权限列表
     */
    List<SysPermission> findPermissionsByRoleId(@Param("roleId") Long roleId);
}
