package com.topov.accessorycompatibility.compatibility.evaluation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CompatibilityMeta {
    private final String compatibilityName;
    private final String evaluationStatus;
    private final String compatibilityStatus;
}
