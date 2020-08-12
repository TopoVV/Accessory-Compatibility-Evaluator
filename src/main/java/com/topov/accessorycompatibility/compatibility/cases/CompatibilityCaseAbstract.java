package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.hardware.Hardware;

public abstract class CompatibilityCaseAbstract<T extends Hardware, U extends Hardware> implements CompatibilityCase {
    protected final T component1;
    protected final U component2;
    protected final String caseName;

    protected CompatibilityCaseAbstract(T component1, U component2) {
        this.component1 = component1;
        this.component2 = component2;
        this.caseName = String.format("%s-%s", component1.getHardwareName(), component2.getHardwareName());
    }

    @Override
    public String getCaseName() {
        return this.caseName;
    }
}
