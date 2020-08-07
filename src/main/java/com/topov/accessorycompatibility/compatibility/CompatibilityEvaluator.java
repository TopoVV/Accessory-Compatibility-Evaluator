package com.topov.accessorycompatibility.compatibility;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import org.springframework.stereotype.Service;

@Service
public class CompatibilityEvaluator {
    public Boolean checkProcessorMotherboardCompatibility(Processor processor, Motherboard motherboard) {
        return processor.getSocket().equals(motherboard.getSocket());
    }
}
