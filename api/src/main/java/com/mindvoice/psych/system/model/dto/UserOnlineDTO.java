package com.mindvoice.psych.system.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserOnlineDTO {
    private  String username;
    private  long loginTime;
}
