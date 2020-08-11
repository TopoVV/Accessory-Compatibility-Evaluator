package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.PcbCpuCompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.PcbRamCompatibilityCase;
import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import com.topov.accessorycompatibility.dto.request.HardwareSpecificationSources;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;
import com.topov.accessorycompatibility.mapper.CompatibilityResultMapper;
import com.topov.accessorycompatibility.receiver.HadrwareReceiverDelegator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class HardwareServiceImpl implements HardwareService {
    private static final Logger LOG = LogManager.getLogger(HardwareServiceImpl.class.getName());

    private final HadrwareReceiverDelegator receiverDelegator;
    private final CompatibilityService compatibilityService;
    private final CompatibilityResultMapper compatibilityResultMapper;

    @Autowired
    public HardwareServiceImpl(CompatibilityResultMapper compatibilityResultMapper,
                               HadrwareReceiverDelegator receiverDelegator,
                               CompatibilityService compatibilityService) {
        this.receiverDelegator = receiverDelegator;
        this.compatibilityService = compatibilityService;
        this.compatibilityResultMapper = compatibilityResultMapper;
    }

    @Override
    public List<CompatibilityResultDto> evaluateHardwareCompatibility(HardwareSpecificationSources hardwareSources) {
        final String cpuUrl = hardwareSources.getCpuUrl();
        final String pcbUrl = hardwareSources.getPcbUrl();
        final String ramUrl = hardwareSources.getRamUrl();
        CompletableFuture<Cpu> cpu = receiverDelegator.receiveProcessor(cpuUrl);
        CompletableFuture<Pcb> pcb = receiverDelegator.receiveMotherboard(pcbUrl);
        CompletableFuture<Ram> ram = receiverDelegator.receiveRam(ramUrl);


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
