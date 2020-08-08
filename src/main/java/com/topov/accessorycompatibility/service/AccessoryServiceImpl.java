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

        CompletableFuture<Processor> processorFuture = specificationsReceiver.receiveProcessorSpecifications("https://ek.ua/AMD-RYZEN-3-MATISSE.htm");
        CompletableFuture<Motherboard> motherboardFuture = specificationsReceiver.receiveMotherboardSpecifications("https://ek.ua/ek-item.php?resolved_name_=ASUS-TUF-B450-PRO-GAMING&view_=tbl");
        CompletableFuture<Ram> ramFuture = specificationsReceiver.receiveRamSpecifications("https://ek.ua/TEAM-GROUP-ELITE-SO-DIMM-DDR4.htm");

        System.out.println(ramFuture.join());
        System.out.println(processorFuture.join());
        System.out.println(motherboardFuture.join());

        CompletableFuture<MotherboardProcessorCompatibility> motherboardProcessor = compatibilityEvaluator.checkCompatibility(processorFuture, motherboardFuture);
        System.out.println(motherboardProcessor.join());


        final long stop = System.currentTimeMillis();
        LOG.info(stop - start);
    }

}
