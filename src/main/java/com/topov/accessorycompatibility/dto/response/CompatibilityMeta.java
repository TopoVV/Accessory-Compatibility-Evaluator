package com.topov.accessorycompatibility.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CompatibilityMeta {
    private String evaluationStatus;
    private String description;

    public CompatibilityMeta(String evaluationStatus, String description) {
        this.evaluationStatus = evaluationStatus;
        this.description = description;
    }
}
