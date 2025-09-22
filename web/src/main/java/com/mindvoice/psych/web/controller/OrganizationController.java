package com.mindvoice.psych.web.controller;

import com.mindvoice.psych.api.dto.SysCompanyDTO;
import com.mindvoice.psych.api.dto.SysDepartmentDTO;
import com.mindvoice.psych.api.service.SysCompanyService;
import com.mindvoice.psych.api.service.SysDepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Organization/manager")
@AllArgsConstructor
public class OrganizationController {

    private final SysCompanyService companyService;

    private final SysDepartmentService departmentService;

    /**
     * 获取所有公司
     */
    @GetMapping("/companies")
    public List<SysCompanyDTO> getAllCompanies(String name) {
        return companyService.getAllCompanies(name);
    }

    @GetMapping("/departments")
    public List<SysDepartmentDTO> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    /**
     * 根据公司ID获取部门列表
     */
    @GetMapping("/companies/{companyId}/departments")
    public List<SysDepartmentDTO> getDepartmentsByCompany(@PathVariable Long companyId) {
        return departmentService.getDepartmentsByCompanyId(companyId);
    }

}
