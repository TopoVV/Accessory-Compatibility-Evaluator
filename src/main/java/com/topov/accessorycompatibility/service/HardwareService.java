package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import com.topov.accessorycompatibility.dto.request.HardwareSpecificationSources;

import java.util.List;

public interface HardwareService {
    List<CompatibilityResultDto> evaluateHardwareCompatibility(HardwareSpecificationSources hardwareSources);
}
