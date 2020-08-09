package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class CompatibilityEvaluatorImpl implements CompatibilityEvaluator {
    private static final String MOTHERBOARD_PROCESSOR_SOCKET_INCOMPATIBLE = "The motherboard socket (%s) is not compatible with the processor socket (%s)";
    private static final String MOTHERBOARD_RAM_TYPES_INCOMPATIBLE = "The motherboard ram socket (%s) is not compatible with the ram type (%s)";

    private static final Logger LOG = LogManager.getLogger(CompatibilityEvaluatorImpl.class.getName());

    @Async
    public CompletableFuture<Compatibility>
    evaluateProcessorMotherboardCompatibility(CompletableFuture<Processor> processor, CompletableFuture<Motherboard> motherboard) {
        LOG.info("Evaluating motherboard and processor compatibility: " + Thread.currentThread().getName());
        List<String> incompatibilities = new ArrayList<>();
        return processor.thenCombine(motherboard, (prc, mbr) -> {
            final String processorSocket = prc.getSocket();
            final String motherboardSocket = mbr.getSocket();
            if (!processorSocket.equals(motherboardSocket)) {
                incompatibilities.add(String.format(MOTHERBOARD_PROCESSOR_SOCKET_INCOMPATIBLE, processorSocket, motherboardSocket));
            }
            return generateCompatibility(incompatibilities);
        }).exceptionally(throwable -> {
            final String error = throwable.getCause().getMessage();
            return Compatibility.builder()
                                .incompatibilities(incompatibilities)
                                .evaluationStatus("failed")
                                .description(error)
                                .build();
        });
    }
    
    @Async
    public CompletableFuture<Compatibility>
    evaluateMotherboardRamCompatibility(CompletableFuture<Ram> ram, CompletableFuture<Motherboard> motherboard) {
        LOG.info("Evaluating motherboard and ram compatibility: " + Thread.currentThread().getName());
        List<String> incompatibilities = new ArrayList<>();
        return ram.thenCombine(motherboard, (rm, mbr) -> {
            final String ramType = rm.getType();
            final String motherboardRamType = mbr.getRamType();
            if (!ramType.equals(motherboardRamType)) {
                incompatibilities.add(String.format(MOTHERBOARD_RAM_TYPES_INCOMPATIBLE, motherboardRamType, ramType));
            }
            return generateCompatibility(incompatibilities);
        }).exceptionally(throwable -> {
            final String error = throwable.getCause().getMessage();
            return Compatibility.builder()
                                .incompatibilities(incompatibilities)
                                .evaluationStatus("failed")
                                .description(error)
                                .build();
        });
    }

    private Compatibility generateCompatibility(List<String> incompatibilities) {
        Compatibility.CompatibilityBuilder builder = Compatibility.builder();
        if(incompatibilities.size() == 0) {
            builder.description("All components are compatible");
        } else {
            builder.description("Some incompatibilities detected");
        }
        return builder.evaluationStatus("success")
                      .incompatibilities(incompatibilities)
                      .build();
    }
}
