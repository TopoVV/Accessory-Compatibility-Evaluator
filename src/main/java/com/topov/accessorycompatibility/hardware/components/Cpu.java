package com.topov.accessorycompatibility.hardware.components;

import com.topov.accessorycompatibility.hardware.Hardware;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class Cpu extends Hardware {
    private String socket;
    private Integer cores;
    private Integer threads;
    private Long frequency;
    private Long heatRelease;
}
