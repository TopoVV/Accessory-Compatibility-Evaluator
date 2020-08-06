package com.topov.accessorycompatibility.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Processor {
    private String socket;
    private Long heatRelease;
}
