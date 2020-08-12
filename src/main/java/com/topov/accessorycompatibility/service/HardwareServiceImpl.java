package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.PcbCpuCompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.PcbRamCompatibilityCase;
import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import com.topov.accessorycompatibility.dto.request.HardwareSpecificationSources;
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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
public class HardwareServiceImpl implements HardwareService {
    private static final Logger LOG = LogManager.getLogger(HardwareServiceImpl.class.getName());

    private final HadrwareReceiverInvoker receiverInvoker;
    private final CompatibilityService compatibilityService;
    private final CompatibilityResultMapper compatibilityResultMapper;

    @Autowired
    public HardwareServiceImpl(CompatibilityResultMapper compatibilityResultMapper,
                               HadrwareReceiverInvoker receiverInvoker,
                               CompatibilityService compatibilityService) {
        this.receiverInvoker = receiverInvoker;
        this.compatibilityService = compatibilityService;
        this.compatibilityResultMapper = compatibilityResultMapper;
    }

    @Override
    public List<CompatibilityResultDto> evaluateHardwareCompatibility(HardwareSpecificationSources hardwareSources) {
        final FutureHardwareHolder build = FutureHardwareHolder.builder()
                   .futureCpu(receiverInvoker.invokeReceiver(hardwareSources.getCpuSource()))
                   .futurePcb(receiverInvoker.invokeReceiver(hardwareSources.getPcbSource()))
                   .futureRam(receiverInvoker.invokeReceiver(hardwareSources.getRamSource()))
                   .build();

        return build.getCompatibilityCases().stream()
                   .map(compatibilityService::evaluateCompatibility)
                   .map(CompletableFuture::join)
                   .map(compatibilityResultMapper::toDto)
                   .collect(toList());
    }
}
