package com.topov.accessorycompatibility.compatibility;

import java.util.List;

public interface CompatibilityEvaluationCommand {
    List<Incompatibility> evaluate();
}
