package com.mindvoice.psych.core.service.impl;

import com.mindvoice.psych.api.dto.SysCompanyDTO;
import com.mindvoice.psych.api.service.SysCompanyService;
import com.mindvoice.psych.infra.domain.SysCompany;
import com.mindvoice.psych.infra.mapper.SysCompanyMapper;
import jakarta.annotation.Resource;

import java.util.List;

public class SysCompanyServiceImpl implements SysCompanyService {

    @Resource
    private SysCompanyMapper sysCompanyMapper;
    @Override
    public List<SysCompanyDTO> getAllCompanies(String name) {
        List<SysCompany> sysCompanies = sysCompanyMapper.getAllCompanies(name);

        return List.of();
    }
}
