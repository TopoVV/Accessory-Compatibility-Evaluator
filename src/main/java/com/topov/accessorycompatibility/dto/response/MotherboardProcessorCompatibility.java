package com.topov.accessorycompatibility.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@NoArgsConstructor
public class MotherboardProcessorCompatibility extends CompatibilityMeta {
    private Boolean isSocketCompatible;

    public MotherboardProcessorCompatibility(String evaluationStatus, String description) {
        super(evaluationStatus, description);
    }
}
