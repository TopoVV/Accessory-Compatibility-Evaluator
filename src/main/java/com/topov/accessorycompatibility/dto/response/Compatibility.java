package com.topov.accessorycompatibility.dto.response;

import com.topov.accessorycompatibility.dto.Incompatibility;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(callSuper = true)
public class Compatibility extends CompatibilityMeta {
    public static Compatibility failed(String cause) {
        return Compatibility.builder()
                     .incompatibilities(new ArrayList<>())
                     .evaluationStatus(cause)
                     .compatibilityStatus("undefined")
                     .build();
    }

    private final List<Incompatibility> incompatibilities;

    @Builder
    public Compatibility(String evaluationStatus, String compatibilityStatus, List<Incompatibility> incompatibilities) {
        super(evaluationStatus, compatibilityStatus);
        this.incompatibilities = incompatibilities;
    }
}
