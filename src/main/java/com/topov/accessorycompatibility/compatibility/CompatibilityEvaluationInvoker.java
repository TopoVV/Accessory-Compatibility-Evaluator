package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.compatibility.command.CompatibilityCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
public class CompatibilityEvaluationInvoker {
    private static final Logger LOG = LogManager.getLogger(CompatibilityEvaluationInvoker.class.getName());

    @Async
    public CompletableFuture<CompatibilityResult> invokeEvaluation(CompatibilityCase compatibilityCase) {
        LOG.info(String.format("Evaluating compatibility: %s", compatibilityCase));
        final String caseName = compatibilityCase.getCaseName();
        try {
            final List<Incompatibility> incompatibilities = compatibilityCase.evaluateCompatibility();

            if (incompatibilities.isEmpty()) {
                final var compatible = CompatibilityResult.compatible(caseName);
                return CompletableFuture.completedFuture(compatible);
            } else {
                final var incompatible = CompatibilityResult.incompatible(caseName, incompatibilities);
                return CompletableFuture.completedFuture(incompatible);
            }
        } catch (Exception e) {
            LOG.error("Compatibility evaluation error: " + e);
            final String message = e.getCause().getMessage();
            final var failed = CompatibilityResult.failed(message, caseName);
            return CompletableFuture.completedFuture(failed);
        }
    }
}
