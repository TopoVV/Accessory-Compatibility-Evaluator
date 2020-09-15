package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;

import java.util.List;

/**
 * The compatibility evaluation logic is implemented as the Command design pattern for each pair of hardware components
 * which compatibility must be checked. This interface is common for all command implementations.
 */


public interface CompatibilityEvaluationCommand {
    List<Incompatibility> evaluate();
}
