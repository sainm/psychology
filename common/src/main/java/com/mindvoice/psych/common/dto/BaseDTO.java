package com.mindvoice.psych.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 更新人ID
     */
    private Long updateBy;
}
