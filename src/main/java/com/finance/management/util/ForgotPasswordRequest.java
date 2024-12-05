package com.finance.management.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPasswordRequest {
    @JsonProperty("reset_token")
    private String resetToken;
    @JsonProperty("new_password")
    private String newPassword;
    @JsonProperty("confirm_new_password")
    private String confirmNewPassword;
}
