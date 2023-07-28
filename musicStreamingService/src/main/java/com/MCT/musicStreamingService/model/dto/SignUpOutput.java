package com.MCT.musicStreamingService.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpOutput {
    private String signUpMessage;
    private Boolean signUpStatus;
}
