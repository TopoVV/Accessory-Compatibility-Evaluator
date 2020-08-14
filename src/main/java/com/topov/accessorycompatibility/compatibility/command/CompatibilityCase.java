package com.topov.accessorycompatibility.compatibility.command;

import com.topov.accessorycompatibility.compatibility.evaluation.Incompatibility;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Data
@AllArgsConstructor
public class CompatibilityCase {
    private String caseName;
    private CompletableFuture<CompatibilityEvaluationCommand> compatibilityCase;

    public List<Incompatibility> evaluateCompatibility() throws ExecutionException, InterruptedException {
        return compatibilityCase.join().evaluate();
    }
}
