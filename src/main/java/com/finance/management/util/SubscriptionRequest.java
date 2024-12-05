package com.finance.management.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionRequest {
    @JsonProperty("service_menu_id")
    private Long serviceMenuId;
    @JsonProperty("subscription_id")
    private Long subscriptionId;
    @JsonProperty("number_of_sessions")
    private int numberOfSessions;
}
