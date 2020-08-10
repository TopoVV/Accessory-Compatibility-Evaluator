package com.topov.accessorycompatibility.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HardwareSpecificationSources {
    private String cpuUrl;
    private String pcbUrl;
    private String ramUrl;
}
