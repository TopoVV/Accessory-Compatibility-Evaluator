package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.compatibility.Incompatibility;

import java.util.List;

public interface ICompatibilityCase {
    List<Incompatibility> evaluate();
    String getCaseName();
}
