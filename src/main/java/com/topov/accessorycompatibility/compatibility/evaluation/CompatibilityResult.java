package com.topov.accessorycompatibility.compatibility.evaluation;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class CompatibilityResult extends CompatibilityMeta {
    public static CompatibilityResult failed(String cause) {
        return CompatibilityResult.builder()
                          .incompatibilities(new ArrayList<>())
                          .compatibilityStatus("undefined")
                          .evaluationStatus(cause)
                          .build();
    }

    private final List<Incompatibility> incompatibilities;

    @Builder
    public CompatibilityResult(String evaluationStatus, String compatibilityName, String compatibilityStatus, List<Incompatibility> incompatibilities) {
        super(compatibilityName, evaluationStatus, compatibilityStatus);
        this.incompatibilities = incompatibilities;
    }
}
