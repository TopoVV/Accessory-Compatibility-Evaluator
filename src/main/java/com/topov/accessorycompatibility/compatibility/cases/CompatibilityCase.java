package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.dto.Incompatibility;

import java.util.List;

public interface CompatibilityCase {
    List<Incompatibility> evaluate();
}
