package com.mindvoice.psych.core.service.impl;

import com.mindvoice.psych.api.dto.SysDepartmentDTO;
import com.mindvoice.psych.api.service.SysDepartmentService;
import com.mindvoice.psych.infra.domain.SysDepartment;
import com.mindvoice.psych.infra.mapper.SysDepartmentMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {

    @Resource
    private SysDepartmentMapper sysDepartmentMapper;
    @Override
    public List<SysDepartmentDTO> getDepartmentsByCompanyId(Long companyId) {
        return List.of();
    }

    @Override
    public List<SysDepartmentDTO> getAllDepartments() {
        List<SysDepartment> sysDepartment = sysDepartmentMapper.getAllDepartments();
        return List.of();
    }
}
