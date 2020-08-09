package com.topov.accessorycompatibility.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CompatibilityMeta {
    protected final String evaluationStatus;
    protected final String compatibilityStatus;
}
