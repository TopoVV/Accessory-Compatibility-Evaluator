package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.hardware.Hardware;

/**
 * A subclass of this class exists for each pair of hardware, which compatibility must be evaluated.
 * The compatibility evaluation logic is implemented with Command design pattern.
 * @param <T> - first component type (CPU, PCB, RAM etc)
 * @param <U> - second component type (CPU, PCB, RAM etc)
 */
public abstract class CompatibilityEvaluationCommandAbstract<T extends Hardware, U extends Hardware> implements CompatibilityEvaluationCommand {
    protected T firstComponent;
    protected U secondComponent;

    protected CompatibilityEvaluationCommandAbstract(T firstComponent, U secondComponent) {
        this.firstComponent = firstComponent;
        this.secondComponent = secondComponent;
    }
}
