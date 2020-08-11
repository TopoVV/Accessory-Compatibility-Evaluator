package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.compatibility.Incompatibility;
import com.topov.accessorycompatibility.hardware.Hardware;

import java.util.List;

public abstract class CompatibilityCase<T extends Hardware, U extends Hardware> {
    protected final T component1;
    protected final U component2;

    protected CompatibilityCase(T component1, U component2) {
        this.component1 = component1;
        this.component2 = component2;
    }

    public abstract List<Incompatibility> evaluate();
}
