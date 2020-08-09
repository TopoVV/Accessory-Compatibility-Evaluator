package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCaseFactory;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityEvaluator;
import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.hardware.compatibility.CompatiblePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class CompatibilityServiceImpl implements CompatibilityService {
    private static final Logger LOG = LogManager.getLogger(CompatibilityServiceImpl.class.getName());
    private final CompatibilityEvaluator compatibilityEvaluator;
    private final CompatibilityCaseFactory factory;

    @Autowired
    public CompatibilityServiceImpl(CompatibilityEvaluator compatibilityEvaluator, CompatibilityCaseFactory factory) {
        this.compatibilityEvaluator = compatibilityEvaluator;
        this.factory = factory;
    }

    @Async
    @Override
    public CompletableFuture<Compatibility> evaluateCompatibility(CompletableFuture<CompatiblePair> pair) {
        try {
            final CompatibilityCase command = factory.createCommand(pair.join());
            final Compatibility compatibility = compatibilityEvaluator.startEvaluation(command);
            return CompletableFuture.completedFuture(compatibility);
        } catch (Exception e) {
            LOG.warn("Compatibility evaluation error: " + e);
            return CompletableFuture.completedFuture(Compatibility.failed(e.getMessage()));
        }
    }
}
