package com.topov.accessorycompatibility.hardware.compatibility;

import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.Hardware;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;

public class CompatiblePairImpl implements CompatiblePair {
    private final EvaluationCase evaluationCase;
    private final Hardware component1;
    private final Hardware component2;

    public CompatiblePairImpl(Hardware component1, Hardware component2, EvaluationCase evaluationCase) {
        this.component1 = component1;
        this.component2 = component2;
        this.evaluationCase = evaluationCase;
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
    public EvaluationCase getEvaluationCase() {
        return this.evaluationCase;
    }

    public enum EvaluationCase {
        MOTHERBOARD_PROCESSOR,
        MOTHERBOARD_RAM
    }
}
