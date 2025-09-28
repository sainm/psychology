package com.mindvoice.psych.common.base;

import lombok.Data;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Data
@ToString
public class BaseVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
}
