package com.mindvoice.psych.infra.mapper;

import com.mindvoice.psych.infra.domain.SysDepartment;

import java.util.List;

public interface SysDepartmentMapper {
    List<SysDepartment> getAllDepartments();
}
