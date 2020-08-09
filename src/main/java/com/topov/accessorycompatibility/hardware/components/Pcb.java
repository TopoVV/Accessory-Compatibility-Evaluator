package com.topov.accessorycompatibility.hardware.components;

import com.topov.accessorycompatibility.hardware.Hardware;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class Pcb extends Hardware {
    private String socket;
    private String chipset;
    private String formFactor;
    private Integer maxRam;
    private Integer ramFrequency;
    private String ramFormFactor;
    private String ramType;
}
