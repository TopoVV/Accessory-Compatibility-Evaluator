package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCaseAbstract;
import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import com.topov.accessorycompatibility.hardware.Hardware;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityEvaluationInvoker {
    private static final Logger LOG = LogManager.getLogger(CompatibilityEvaluationInvoker.class.getName());

    public CompatibilityResult invokeEvaluation(CompatibilityCase compatibilityCase) {
        LOG.info(String.format("Evaluating compatibility: %s", compatibilityCase));
        final List<Incompatibility> incompatibilities = compatibilityCase.evaluate();
        if(incompatibilities.size() > 0) {
            return new CompatibilityResult("success", compatibilityCase.getCaseName(), "incompatible", incompatibilities);
        } else {
            return new CompatibilityResult("success", compatibilityCase.getCaseName(), "compatible", incompatibilities);
        }
    }
}
