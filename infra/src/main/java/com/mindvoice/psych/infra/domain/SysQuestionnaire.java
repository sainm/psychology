package com.mindvoice.psych.infra.domain;

import com.mindvoice.psych.common.pojo.BaseDO;
import lombok.Data;

@Data
public class SysQuestionnaire extends BaseDO {
    private Long id;
    private String code;
    private String name;
    private String description;
    private Integer status;
}
