package com.topov.accessorycompatibility.dto.response;

import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import lombok.Data;

import java.util.List;

@Data
public class CompatibilityResponse {
    private final List<CompatibilityResultDto> compatibilityResults;

    public CompatibilityResponse(List<CompatibilityResultDto> compatibilityResults) {
        this.compatibilityResults = compatibilityResults;
    }
}
