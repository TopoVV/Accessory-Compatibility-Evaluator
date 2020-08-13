package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.CompatibilityEvaluationInvoker;
import com.topov.accessorycompatibility.compatibility.command.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
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
    public CompletableFuture<CompatibilityResult> evaluateCompatibility(CompatibilityCase compatibilityCase) {
        return evaluationInvoker.invokeEvaluation(compatibilityCase);
    }
}
