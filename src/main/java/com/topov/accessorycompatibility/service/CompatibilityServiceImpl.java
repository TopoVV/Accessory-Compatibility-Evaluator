package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.CompatibilityEvaluationInvoker;
import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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

    @Async
    @Override
    public CompletableFuture<CompatibilityResult> evaluateCompatibility(CompletableFuture<CompatibilityCase> compatibilityCase) {
        try {
            CompatibilityResult compatibilityResult = evaluationInvoker.invokeEvaluation(compatibilityCase.join());
            return CompletableFuture.completedFuture(compatibilityResult);
        } catch (Exception e) {
            LOG.error("Compatibility evaluation error: " + e);
            final String message = e.getCause().getMessage();
            return CompletableFuture.completedFuture(CompatibilityResult.failed(message));
        }
    }
}
