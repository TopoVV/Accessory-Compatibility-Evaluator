package com.topov.accessorycompatibility.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.topov.accessorycompatibility.compatibility.Incompatibility;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CompatibilityResultDto {
    private String compatibilityName;
    private String evaluationStatus;
    private String compatibilityStatus;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<Incompatibility> incompatibilities;

}
