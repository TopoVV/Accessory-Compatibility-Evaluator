package com.topov.accessorycompatibility.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Motherboard {
    private String socket;
    private String chipset;
    private String formFactor;
    private Integer maxRam;
    private Integer ramFrequency;
    private String ramFormFactor;
    private String ramType;
}
