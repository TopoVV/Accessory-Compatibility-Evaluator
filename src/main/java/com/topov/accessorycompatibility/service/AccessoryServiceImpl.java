package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.CompatibilityEvaluator;
import com.topov.accessorycompatibility.dto.response.MotherboardProcessorCompatibility;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.receiver.SpecificationsReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AccessoryServiceImpl implements AccessoryService {
    private static final Logger LOG = LogManager.getLogger(AccessoryServiceImpl.class.getName());

    private final SpecificationsReceiver specificationsReceiver;
    private final CompatibilityEvaluator compatibilityEvaluator;

    @Autowired
    public AccessoryServiceImpl(SpecificationsReceiver specificationsReceiver, CompatibilityEvaluator compatibilityEvaluator) {
        this.specificationsReceiver = specificationsReceiver;
        this.compatibilityEvaluator = compatibilityEvaluator;
    }

    @Override
    public void doWork() {
        final long start = System.currentTimeMillis();
        String processorName = "INTEL i5-9400 BOX";
        String motherboardName = "ASROCK B450M PRO4 F";
        String ramName = "CRUCIAL-BALLISTIX-DDR4-2X8GB";
        CompletableFuture<Processor> processorFuture = specificationsReceiver.receiveProcessorSpecifications(processorName);
        CompletableFuture<Motherboard> motherboardFuture = specificationsReceiver.receiveMotherboardSpecifications(motherboardName);
        CompletableFuture<Ram> ramFuture = specificationsReceiver.receiveRamSpecifications(ramName);

        System.out.println(processorFuture.join());
        System.out.println(motherboardFuture.join());
        CompletableFuture<MotherboardProcessorCompatibility> motherboardProcessor =
            compatibilityEvaluator.checkCompatibility(processorFuture, motherboardFuture);
        System.out.println(motherboardProcessor.join());


        final long stop = System.currentTimeMillis();
        LOG.info(stop - start);
    }

}
