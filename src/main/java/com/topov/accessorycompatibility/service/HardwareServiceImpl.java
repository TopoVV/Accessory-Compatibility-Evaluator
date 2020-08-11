package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.PcbCpuCompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.PcbRamCompatibilityCase;
import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import com.topov.accessorycompatibility.dto.request.HardwareSpecificationSources;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.hardware.Hardware;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;
import com.topov.accessorycompatibility.hardware.sources.CpuSource;
import com.topov.accessorycompatibility.hardware.sources.PcbSource;
import com.topov.accessorycompatibility.hardware.sources.RamSource;
import com.topov.accessorycompatibility.mapper.CompatibilityResultMapper;
import com.topov.accessorycompatibility.receiver.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class HardwareServiceImpl implements HardwareService {
    private static final Logger LOG = LogManager.getLogger(HardwareServiceImpl.class.getName());

    private final HadrwareReceiverInvoker receiverDelegator;
    private final CompatibilityService compatibilityService;
    private final CompatibilityResultMapper compatibilityResultMapper;

    @Autowired
    public HardwareServiceImpl(CompatibilityResultMapper compatibilityResultMapper,
                               HadrwareReceiverInvoker receiverDelegator,
                               CompatibilityService compatibilityService) {
        this.receiverDelegator = receiverDelegator;
        this.compatibilityService = compatibilityService;
        this.compatibilityResultMapper = compatibilityResultMapper;
    }

    @Override
    public List<CompatibilityResultDto> evaluateHardwareCompatibility(HardwareSpecificationSources hardwareSources) {

        final CompletableFuture<Cpu> cpu = receiverDelegator.invokeReceiver(new CpuSource(hardwareSources.getCpuUrl()));
        final CompletableFuture<Pcb> pcb = receiverDelegator.invokeReceiver(new PcbSource(hardwareSources.getPcbUrl()));
        final CompletableFuture<Ram> ram = receiverDelegator.invokeReceiver(new RamSource(hardwareSources.getRamUrl()));

        final CompletableFuture<CompatibilityCase<Pcb, Cpu>> pcbCpu = pcb.thenCombine(cpu, PcbCpuCompatibilityCase::new);
        final CompletableFuture<CompatibilityCase<Pcb, Ram>> pcbRam = pcb.thenCombine(ram, PcbRamCompatibilityCase::new);


        final CompletableFuture<CompatibilityResult> pcbCpuCompatibility = compatibilityService.evaluateCompatibility(pcbCpu);
        final CompletableFuture<CompatibilityResult> pcbRamCompatibility = compatibilityService.evaluateCompatibility(pcbRam);

        return Stream.of(pcbCpuCompatibility, pcbRamCompatibility)
                     .map(CompletableFuture::join)
                     .map(compatibilityResultMapper::toDto)
                     .collect(toList());
    }


}
