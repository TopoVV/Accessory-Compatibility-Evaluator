package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.dto.response.Compatibility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompatibilityEvaluatorImpl implements CompatibilityEvaluator {
    @Override
    public Compatibility startEvaluation(CompatibilityEvaluationCommand command) {
        final List<Incompatibility> incompatibilities = command.evaluate();
        if(incompatibilities.size() > 0) {
            return new Compatibility("success", "incompatible", incompatibilities);
        } else {
            return new Compatibility("success", "compatible", incompatibilities);
        }
    }
}
