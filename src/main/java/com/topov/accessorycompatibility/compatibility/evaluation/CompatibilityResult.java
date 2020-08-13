package com.topov.accessorycompatibility.compatibility.evaluation;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class CompatibilityResult extends CompatibilityMeta {
    public static CompatibilityResult failed(String cause, String compatibilityName) {
        return CompatibilityResult.builder()
                          .incompatibilities(Collections.emptyList())
                          .compatibilityName(compatibilityName)
                          .evaluationStatus("fail")
                          .compatibilityStatus("undefined")
                          .evaluationStatus(cause)
                          .build();
    }

    public static CompatibilityResult compatible(String compatibilityName) {
        return CompatibilityResult.builder()
                                  .compatibilityName(compatibilityName)
                                  .incompatibilities(Collections.emptyList())
                                  .compatibilityStatus("compatible")
                                  .evaluationStatus("success")
                                  .build();
    }
    public static CompatibilityResult incompatible(String compatibilityName, List<Incompatibility> incompatibilities) {
        return CompatibilityResult.builder()
                                  .compatibilityName(compatibilityName)
                                  .incompatibilities(incompatibilities)
                                  .compatibilityStatus("incompatible")
                                  .evaluationStatus("success")
                                  .build();
    }

    private final List<Incompatibility> incompatibilities;

    @Builder
    public CompatibilityResult(String evaluationStatus, String compatibilityName, String compatibilityStatus, List<Incompatibility> incompatibilities) {
        super(compatibilityName, evaluationStatus, compatibilityStatus);
        this.incompatibilities = incompatibilities;
    }
}
