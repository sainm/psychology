package com.mindvoice.psych.api.service;

import com.mindvoice.psych.api.dto.SysCompanyDTO;

import java.util.List;

public interface SysCompanyService {
    List<SysCompanyDTO> getAllCompanies(String name);
}
