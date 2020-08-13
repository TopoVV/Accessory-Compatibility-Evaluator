package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;

import java.util.List;

public interface CompatibilityEvaluationCommand {
    List<Incompatibility> evaluate();
}
