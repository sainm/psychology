package com.mindvoice.psych.api.service;

import com.mindvoice.psych.api.dto.SysDepartmentDTO;

import java.util.List;

public interface SysDepartmentService {
    List<SysDepartmentDTO> getDepartmentsByCompanyId(Long companyId);

    List<SysDepartmentDTO> getAllDepartments();
}
