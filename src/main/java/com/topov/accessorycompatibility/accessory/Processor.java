package com.topov.accessorycompatibility.accessory;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class Processor {
    private String socket;
    private Long heatRelease;
}
