package com.finance.management.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardResponse {
    @JsonProperty("card_no")
    private String cardNo;
    private String cvv;
    @JsonProperty("expired_date")
    private String expiredDate;
    @JsonProperty("owner_name")
    private String ownerName;
}
