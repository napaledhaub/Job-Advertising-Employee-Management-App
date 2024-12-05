package com.finance.management.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddServiceMenuRequest {
    private String name;
    @JsonProperty("price_per_session")
    private double pricePerSession;
    @JsonProperty("total_sessions")
    private int totalSessions;
    private String schedule;
    @JsonProperty("duration_in_minutes")
    private int durationInMinutes;
    @JsonProperty("exercise_list")
    private List<ExcerciseRequest> exerciseList;
}
