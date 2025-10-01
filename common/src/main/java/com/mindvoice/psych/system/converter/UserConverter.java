package com.mindvoice.psych.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.mindvoice.psych.common.model.Option;
import com.mindvoice.psych.system.model.bo.UserBO;
import com.mindvoice.psych.system.model.entity.User;
import com.mindvoice.psych.system.model.form.UserForm;
import com.mindvoice.psych.system.model.form.UserProfileForm;
import com.mindvoice.psych.system.model.vo.UserPageVO;
import com.mindvoice.psych.system.model.dto.CurrentUserDTO;

import com.mindvoice.psych.system.model.dto.UserImportDTO;

import com.mindvoice.psych.system.model.vo.UserProfileVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 用户对象转换器
 *
 * @author liu
 * @since 2025/6/8
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    UserPageVO toPageVo(UserBO bo);

    Page<UserPageVO> toPageVo(Page<UserBO> bo);

    UserForm toForm(User entity);

    @InheritInverseConfiguration(name = "toForm")
    User toEntity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    CurrentUserDTO toCurrentUserDto(User entity);

    User toEntity(UserImportDTO vo);


    UserProfileVO toProfileVo(UserBO bo);

    User toEntity(UserProfileForm formData);

    @Mappings({
            @Mapping(target = "label", source = "nickname"),
            @Mapping(target = "value", source = "id")
    })
    Option<String> toOption(User entity);

    List<Option<String>> toOptions(List<User> list);
}
