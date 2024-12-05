package com.finance.management.util;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillRequest {
    @JsonProperty("expected_bill_amount")
    private BigDecimal expectedBillAmount;
}
