package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.CompatibilityEvaluator;
import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.receiver.SpecsReceiverDelegator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AccessoryServiceImpl implements AccessoryService {
    private static final Logger LOG = LogManager.getLogger(AccessoryServiceImpl.class.getName());

    private final SpecsReceiverDelegator receiverDelegator;
    private final CompatibilityEvaluator compatibilityEvaluator;

    @Autowired
    public AccessoryServiceImpl(SpecsReceiverDelegator receiverDelegator, CompatibilityEvaluator compatibilityEvaluator) {
        this.receiverDelegator = receiverDelegator;
        this.compatibilityEvaluator = compatibilityEvaluator;
    }

    @Override
    public void doWork() {
        final String processorUrl = "https://ek.ua/AMD-RYZEN-3-MATISSE.htm";
        final String motherboardUrl = "https://ek.ua/ek-item.php?resolved_name_=ASUS-M5A78L-M-LX3&view_=tbl";
        final String ramUrl = "https://ek.ua/TEAM-GROUP-ELITE-SO-DIMM-DDR4.htm";
        CompletableFuture<Processor> processor = receiverDelegator.receiveProcessor(processorUrl);
        CompletableFuture<Motherboard> motherboard = receiverDelegator.receiveMotherboard(motherboardUrl);
        CompletableFuture<Ram> ram = receiverDelegator.receiveRam(ramUrl);

        CompletableFuture<Compatibility> motherboardProcessor =
            compatibilityEvaluator.evaluateProcessorMotherboardCompatibility(processor, motherboard);
        CompletableFuture<Compatibility> ramMotherboard =
            compatibilityEvaluator.evaluateMotherboardRamCompatibility(ram, motherboard);

        System.out.println(motherboardProcessor.join());
        System.out.println(ramMotherboard.join());

    }

}
