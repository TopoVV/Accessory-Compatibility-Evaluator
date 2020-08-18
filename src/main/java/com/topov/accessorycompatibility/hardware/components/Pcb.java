package com.topov.accessorycompatibility.hardware.components;

import com.topov.accessorycompatibility.hardware.Hardware;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Pcb extends Hardware {
    private final String socket;
    private final String chipset;
    private final String formFactor;
    private final Integer maxRam;
    private final Integer ramFrequency;
    private final String ramFormFactor;
    private final String ramType;

    @Builder
    public Pcb(String socket, String chipset, String formFactor, Integer maxRam, Integer ramFrequency, String ramFormFactor, String ramType) {
        this.socket = socket;
        this.chipset = chipset;
        this.formFactor = formFactor;
        this.maxRam = maxRam;
        this.ramFrequency = ramFrequency;
        this.ramFormFactor = ramFormFactor;
        this.ramType = ramType;
    }

    public boolean isCompatibleWithCpuSocket(String cpuSocket) {
        return this.socket.equals(cpuSocket);
    }

    public boolean isCompatibleWithRamType(String ramType) {
        return this.ramType.equals(ramType);
    }

    public boolean isCompatibleWithRamFormFactor(String ramFormFactor) {
        return this.ramFormFactor.equals(ramFormFactor);
    }


}
