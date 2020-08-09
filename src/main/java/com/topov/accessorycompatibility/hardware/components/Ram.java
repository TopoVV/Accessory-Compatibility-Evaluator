package com.topov.accessorycompatibility.hardware.components;

import com.topov.accessorycompatibility.hardware.Hardware;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class Ram extends Hardware {
    private String formFactor;
    private Integer frequency;
    private String timings;
    private Long voltage;
    private String type;
}
