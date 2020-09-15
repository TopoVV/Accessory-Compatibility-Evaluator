package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.CompatibilityEvaluationInvoker;
import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import com.topov.accessorycompatibility.dto.request.HardwareSpecificationSources;
import com.topov.accessorycompatibility.mapper.CompatibilityResultMapper;
import com.topov.accessorycompatibility.hardware.FutureHardwareHolder;
import com.topov.accessorycompatibility.receiver.HadrwareReceiverInvoker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

@Service
public class HardwareServiceImpl implements HardwareService {
    private static final Logger LOG = LogManager.getLogger(HardwareServiceImpl.class.getName());

    private final HadrwareReceiverInvoker receiverInvoker;
    private final CompatibilityEvaluationInvoker compatibilityEvaluationInvoker;
    private final CompatibilityResultMapper compatibilityResultMapper;

    @Autowired
    public HardwareServiceImpl(CompatibilityEvaluationInvoker compatibilityEvaluationInvoker,
                               CompatibilityResultMapper compatibilityResultMapper,
                               HadrwareReceiverInvoker receiverInvoker) {
        this.receiverInvoker = receiverInvoker;
        this.compatibilityEvaluationInvoker = compatibilityEvaluationInvoker;
        this.compatibilityResultMapper = compatibilityResultMapper;
    }

    @Override
    public List<CompatibilityResultDto> evaluateHardwareCompatibility(HardwareSpecificationSources hardwareSources) {

        final FutureHardwareHolder hardware =
            FutureHardwareHolder.builder()
                                .futureCpu(receiverInvoker.invokeReceiver(hardwareSources.getCpuSource()))
                                .futurePcb(receiverInvoker.invokeReceiver(hardwareSources.getPcbSource()))
                                .futureRam(receiverInvoker.invokeReceiver(hardwareSources.getRamSource()))
                                .build();

        return hardware.getCompatibilityCaseHolders().stream()
                       .map(compatibilityEvaluationInvoker::invokeEvaluation)
                       .map(CompletableFuture::join)
                       .map(compatibilityResultMapper::toDto)
                       .collect(toList());
    }
}
