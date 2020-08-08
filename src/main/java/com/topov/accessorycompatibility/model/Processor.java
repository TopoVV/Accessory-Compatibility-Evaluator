package com.topov.accessorycompatibility.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Processor {
    private String socket;
    private Integer cores;
    private Integer threads;
    private Long frequency;
    private Long heatRelease;
}
