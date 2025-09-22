package com.mindvoice.psych.api.dto;

import com.mindvoice.psych.common.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysCompanyDTO extends BaseDTO {

    private Long id;

    private String name;

    private String description;

    private Boolean enabled;
}
