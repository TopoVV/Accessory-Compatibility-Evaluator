package com.topov.accessorycompatibility.compatibility.evaluation;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.compatibility.cases.ICompatibilityCase;
import com.topov.accessorycompatibility.compatibility.Incompatibility;
import com.topov.accessorycompatibility.hardware.Hardware;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityEvaluatorImpl implements CompatibilityEvaluator {
    private static final Logger LOG = LogManager.getLogger(CompatibilityEvaluatorImpl.class.getName());

    @Override
    public <T extends Hardware, U extends Hardware> CompatibilityResult startEvaluation(CompatibilityCase<T, U> compatibilityCase) {
        LOG.info("Evaluating compatibility! " + compatibilityCase);
        final List<Incompatibility> incompatibilities = compatibilityCase.evaluate();
        if(incompatibilities.size() > 0) {
            return new CompatibilityResult("success", "cs-1", "incompatible", incompatibilities);
        } else {
            return new CompatibilityResult("success", "cs-2", "compatible", incompatibilities);
        }
    }
}
