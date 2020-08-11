package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
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

    public <T extends Hardware, U extends Hardware>
    CompatibilityResult invokeEvaluation(CompatibilityCase<T, U> compatibilityCase) {
        LOG.info("Evaluating compatibility! " + compatibilityCase);
        final List<Incompatibility> incompatibilities = compatibilityCase.evaluate();
        if(incompatibilities.size() > 0) {
            return new CompatibilityResult("success", "cs-1", "incompatible", incompatibilities);
        } else {
            return new CompatibilityResult("success", "cs-2", "compatible", incompatibilities);
        }
    }
}
