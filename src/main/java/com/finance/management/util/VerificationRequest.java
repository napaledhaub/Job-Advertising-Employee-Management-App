package com.finance.management.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerificationRequest extends EmailRequest {
    private String otp;
}
