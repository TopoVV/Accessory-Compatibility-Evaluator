package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.Hardware;

import java.util.List;

public interface CompatibilityCase {
    Hardware getComponent1();
    Hardware getComponent2();
    String getCaseName();
    List<Incompatibility> evaluate();
}
