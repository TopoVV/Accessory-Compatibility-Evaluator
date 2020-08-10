package com.topov.accessorycompatibility.compatibility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incompatibility {
    private String incompatibilityCase;
    private String incompatibilityDescription;
}
