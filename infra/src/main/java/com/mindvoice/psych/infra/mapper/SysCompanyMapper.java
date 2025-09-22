package com.mindvoice.psych.infra.mapper;

import com.mindvoice.psych.infra.domain.SysCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysCompanyMapper {
    List<SysCompany> getAllCompanies(@Param("name") String name);
}
