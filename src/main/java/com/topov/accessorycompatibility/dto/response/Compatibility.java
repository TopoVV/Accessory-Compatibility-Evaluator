package com.topov.accessorycompatibility.dto.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class Compatibility extends CompatibilityMeta {
    private final List<String> incompatibilities;

    @Builder
    public Compatibility(String evaluationStatus, String description, List<String> incompatibilities) {
        super(evaluationStatus, description);
        this.incompatibilities = incompatibilities;
    }
}
