package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.hardware.Hardware;

public abstract class CompatibilityCaseAbstract<T extends Hardware, U extends Hardware> implements CompatibilityCase {
    protected final T component1;
    protected final U component2;

    protected CompatibilityCaseAbstract(T component1, U component2) {
        this.component1 = component1;
        this.component2 = component2;
    }
}
