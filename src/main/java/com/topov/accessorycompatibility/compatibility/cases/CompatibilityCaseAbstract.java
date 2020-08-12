package com.topov.accessorycompatibility.compatibility.cases;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.Hardware;

import java.util.List;

public abstract class CompatibilityCaseAbstract<T extends Hardware, U extends Hardware> implements CompatibilityCase {
    protected final T component1;
    protected final U component2;
    protected final String caseName;

    protected CompatibilityCaseAbstract(T component1, U component2) {
        this.component1 = component1;
        this.component2 = component2;
        this.caseName = component1.getHardwareName() + component2.getHardwareName();
    }

    @Override
    public Hardware getComponent1() {
        return this.component1;
    }

    @Override
    public Hardware getComponent2() {
        return this.component2;
    }

    @Override
    public String getCaseName() {
        return this.caseName;
    }
}
