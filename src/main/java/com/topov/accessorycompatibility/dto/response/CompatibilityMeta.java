package com.topov.accessorycompatibility.dto.response;

import lombok.*;

@Getter
@AllArgsConstructor
@ToString
public class CompatibilityMeta {
    protected final String evaluationStatus;
    protected final String description;
}
