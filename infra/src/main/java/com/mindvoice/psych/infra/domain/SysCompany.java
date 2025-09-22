package com.mindvoice.psych.infra.domain;

import com.mindvoice.psych.common.pojo.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysCompany extends BaseDO {
    private Long id;

    private String name;

    private String description;

    private Boolean enabled;
}
