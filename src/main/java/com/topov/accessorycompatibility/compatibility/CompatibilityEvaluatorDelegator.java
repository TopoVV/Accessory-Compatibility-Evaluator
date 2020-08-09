package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.model.Hardware;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class CompatibilityEvaluatorDelegator {
    private static final Logger LOG = LogManager.getLogger(CompatibilityEvaluatorDelegator.class.getName());
    private final CompatibilityEvaluator compatibilityEvaluator;

    @Autowired
    public CompatibilityEvaluatorDelegator(CompatibilityEvaluator compatibilityEvaluator) {
        this.compatibilityEvaluator = compatibilityEvaluator;
    }

    @Async
    public CompletableFuture<Compatibility> evaluateCompatibility(CompletableFuture<? extends Hardware> component1,
                                                                  CompletableFuture<? extends Hardware> component2) {
        new Comp
    }


//    @Async
//    public CompletableFuture<Compatibility>
//    evaluateProcessorMotherboardCompatibility(CompletableFuture<Processor> processor, CompletableFuture<Motherboard> motherboard) {
//        LOG.info("Evaluating motherboard and processor compatibility: " + Thread.currentThread().getName());
//        List<String> incompatibilities = new ArrayList<>();
//        return processor.thenCombine(motherboard, (prc, mbr) -> {
//            final String processorSocket = prc.getSocket();
//            final String motherboardSocket = mbr.getSocket();
//            if (!processorSocket.equals(motherboardSocket)) {
//                incompatibilities.add(String.format(MOTHERBOARD_PROCESSOR_SOCKET_INCOMPATIBLE, processorSocket, motherboardSocket));
//            }
//            return generateCompatibility(incompatibilities);
//        }).exceptionally(throwable -> {
//            final String error = throwable.getCause().getMessage();
//            return Compatibility.builder()
//                                .incompatibilities(incompatibilities)
//                                .evaluationStatus("failed")
//                                .description(error)
//                                .build();
//        });
//    }
//
//    @Async
//    public CompletableFuture<Compatibility>
//    evaluateMotherboardRamCompatibility(CompletableFuture<Ram> ram, CompletableFuture<Motherboard> motherboard) {
//        LOG.info("Evaluating motherboard and ram compatibility: " + Thread.currentThread().getName());
//        List<String> incompatibilities = new ArrayList<>();
//        return ram.thenCombine(motherboard, (rm, mbr) -> {
//            final String ramType = rm.getType();
//            final String motherboardRamType = mbr.getRamType();
//            if (!ramType.equals(motherboardRamType)) {
//                incompatibilities.add(String.format(MOTHERBOARD_RAM_TYPES_INCOMPATIBLE, motherboardRamType, ramType));
//            }
//            return generateCompatibility(incompatibilities);
//        }).exceptionally(throwable -> {
//            final String error = throwable.getCause().getMessage();
//            return Compatibility.builder()
//                                .incompatibilities(incompatibilities)
//                                .evaluationStatus("failed")
//                                .description(error)
//                                .build();
//        });
//    }
//
//    private Compatibility generateCompatibility(List<String> incompatibilities) {
//        Compatibility.CompatibilityBuilder builder = Compatibility.builder();
//        if(incompatibilities.size() == 0) {
//            builder.description("All components are compatible");
//        } else {
//            builder.description("Some incompatibilities detected");
//        }
//        return builder.evaluationStatus("success")
//                      .incompatibilities(incompatibilities)
//                      .build();
//    }
}
