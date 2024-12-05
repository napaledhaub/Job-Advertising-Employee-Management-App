package com.finance.management.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcerciseRequest {
    private String name;
    @JsonProperty("duration_in_minutes")
    private int durationInMinutes;
    private String description;
}
