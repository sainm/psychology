package com.mindvoice.psych.common.model;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 键值对
 *
 * @author haoxr
 * @since 2024/5/25
 */
@Data
@NoArgsConstructor
public class KeyValue {

    public KeyValue(String key, String value) {
        this.key = key;
        this.value = value;
    }

    private String key;

    private String value;

}