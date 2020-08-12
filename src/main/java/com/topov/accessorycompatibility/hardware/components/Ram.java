package com.topov.accessorycompatibility.hardware.components;

import com.topov.accessorycompatibility.hardware.Hardware;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Ram extends Hardware {
    private final String formFactor;
    private final Integer frequency;
    private final String timings;
    private final Long voltage;
    private final String type;

    @Builder
    public Ram(String formFactor, Integer frequency, String timings, Long voltage, String type) {
        super("ram");
        this.formFactor = formFactor;
        this.frequency = frequency;
        this.timings = timings;
        this.voltage = voltage;
        this.type = type;
    }
}
