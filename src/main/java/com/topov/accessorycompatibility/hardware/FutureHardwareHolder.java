package com.topov.accessorycompatibility.hardware;


import com.topov.accessorycompatibility.compatibility.command.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.command.PcbCpuCompatibilityEvaluationCommand;
import com.topov.accessorycompatibility.compatibility.command.PcbRamCompatibilityEvaluationCommand;
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

    public List<CompatibilityCase> getCompatibilityCaseHolders() {
        return List.of(
            new CompatibilityCase("motherboard-processor", this.futurePcb.thenCombine(futureCpu, PcbCpuCompatibilityEvaluationCommand::new)),
            new CompatibilityCase("motherboard-ram", this.futurePcb.thenCombine(futureRam, PcbRamCompatibilityEvaluationCommand::new))
        );
    }
}
