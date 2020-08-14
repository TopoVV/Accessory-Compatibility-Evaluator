package com.topov.accessorycompatibility.compatibility.evaluation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incompatibility {
    private String incompatibilityCase;
    private String incompatibilityDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Incompatibility that = (Incompatibility) o;
        return incompatibilityCase.equals(that.incompatibilityCase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incompatibilityCase);
    }
}
