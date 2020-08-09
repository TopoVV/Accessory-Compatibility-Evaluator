package com.topov.accessorycompatibility.compatibility.evaluation;

import com.topov.accessorycompatibility.compatibility.cases.CompatibilityCase;
import com.topov.accessorycompatibility.dto.Incompatibility;
import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.receiver.HadrwareReceiverDelegator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityEvaluatorImpl implements CompatibilityEvaluator {
    private static final Logger LOG = LogManager.getLogger(CompatibilityEvaluatorImpl.class.getName());

    @Override
    public Compatibility startEvaluation(CompatibilityCase command) {
        LOG.info("Evaluating compatibility! " + command);
        final List<Incompatibility> incompatibilities = command.evaluate();
        if(incompatibilities.size() > 0) {
            return new Compatibility("success", "incompatible", incompatibilities);
        } else {
            return new Compatibility("success", "compatible", incompatibilities);
        }
    }
}
