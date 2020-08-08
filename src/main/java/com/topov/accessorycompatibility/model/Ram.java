package com.topov.accessorycompatibility.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Ram {
    private String formFactor;
    private Integer frequency;
    private String timings;
    private Long voltage;
    private String type;
}
