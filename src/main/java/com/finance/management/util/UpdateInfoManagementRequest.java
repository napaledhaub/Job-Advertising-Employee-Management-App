package com.finance.management.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateInfoManagementRequest {
    @JsonProperty("new_full_name")
    private String newFullName;
    @JsonProperty("new_credit_card_info")
    private CreditCardResponse newCreditCardInfo;
    @JsonProperty("new_password")
    private String newPassword;
}
