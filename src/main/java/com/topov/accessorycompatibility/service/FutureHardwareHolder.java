package com.topov.accessorycompatibility.service;


import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCaseAbstract;
import com.topov.accessorycompatibility.compatibility.cases.PcbCpuCompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.PcbRamCompatibilityCase;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Getter
public class FutureHardwareHolder {
    private final CompletableFuture<Cpu> futureCpu;
    private final CompletableFuture<Pcb> futurePcb;
    private final CompletableFuture<Ram> futureRam;

    @Builder
    public FutureHardwareHolder(CompletableFuture<Cpu> futureCpu, CompletableFuture<Pcb> futurePcb, CompletableFuture<Ram> futureRam) {
        this.futureCpu = futureCpu;
        this.futurePcb = futurePcb;
        this.futureRam = futureRam;
    }

    public List<CompletableFuture<CompatibilityCase>> getCompatibilityCases() {
        return List.of(
            this.futurePcb.thenCombine(futureCpu, PcbCpuCompatibilityCase::new),
            this.futurePcb.thenCombine(futureRam, PcbRamCompatibilityCase::new)
        );
    }
}
