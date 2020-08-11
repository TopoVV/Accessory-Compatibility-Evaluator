package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.CompatibilityEvaluationInvoker;
import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.hardware.Hardware;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class CompatibilityServiceImpl implements CompatibilityService {
    private static final Logger LOG = LogManager.getLogger(CompatibilityServiceImpl.class.getName());
    private final CompatibilityEvaluationInvoker evaluationInvoker;

    @Autowired
    public CompatibilityServiceImpl(CompatibilityEvaluationInvoker evaluationInvoker) {
        this.evaluationInvoker = evaluationInvoker;
    }

    @Override
    public <T extends Hardware, U extends Hardware>
    CompletableFuture<CompatibilityResult> evaluateCompatibility(CompletableFuture<CompatibilityCase<T, U>> compatibilityCase) {
        try {
            final CompatibilityResult compatibilityResult = evaluationInvoker.invokeEvaluation(compatibilityCase.join());
            return CompletableFuture.completedFuture(compatibilityResult);
        } catch (Exception e) {
            LOG.warn("Compatibility evaluation error: " + e);
            return CompletableFuture.completedFuture(CompatibilityResult.failed(e.getMessage()));
        }
    }
}
