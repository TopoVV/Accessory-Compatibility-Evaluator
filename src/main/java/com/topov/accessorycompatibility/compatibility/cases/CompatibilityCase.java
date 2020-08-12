package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;

import java.util.List;

public interface CompatibilityCase {
    String getCaseName();
    List<Incompatibility> evaluate();
}
