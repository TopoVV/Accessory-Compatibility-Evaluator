package com.topov.accessorycompatibility.dto.response;

import com.topov.accessorycompatibility.compatibility.Incompatibility;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString(callSuper = true)
public class Compatibility extends CompatibilityMeta {
    private final List<Incompatibility> incompatibilities;

    @Builder
    public Compatibility(String evaluationStatus, String compatibilityStatus, List<Incompatibility> incompatibilities) {
        super(evaluationStatus, compatibilityStatus);
        this.incompatibilities = incompatibilities;
    }
}
