package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.hardware.Hardware;

/**
 * A subclass of this class exists for each pair of the hardware components, which compatibility must be evaluated.
 * The compatibility evaluation logic is implemented with Command design pattern.
 * @param <T> - the first hardware component type (CPU, PCB, RAM etc)
 * @param <U> - the second hardware component type (CPU, PCB, RAM etc)
 */
public abstract class CompatibilityEvaluationCommandAbstract<T extends Hardware, U extends Hardware> implements CompatibilityEvaluationCommand {
    protected T firstComponent;
    protected U secondComponent;

    protected CompatibilityEvaluationCommandAbstract(T firstComponent, U secondComponent) {
        this.firstComponent = firstComponent;
        this.secondComponent = secondComponent;
    }
}
