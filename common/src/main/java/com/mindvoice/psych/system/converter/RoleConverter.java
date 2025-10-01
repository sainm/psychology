package com.mindvoice.psych.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.mindvoice.psych.common.model.Option;
import com.mindvoice.psych.system.model.entity.Role;
import com.mindvoice.psych.system.model.form.RoleForm;
import com.mindvoice.psych.system.model.vo.RolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色对象转换器
 *
 * @author liu
 * @since 2025/5/29
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    Page<RolePageVO> toPageVo(Page<Role> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option<Long> toOption(Role role);

    List<Option<Long>> toOptions(List<Role> roles);

    Role toEntity(RoleForm roleForm);

    RoleForm toForm(Role entity);
}