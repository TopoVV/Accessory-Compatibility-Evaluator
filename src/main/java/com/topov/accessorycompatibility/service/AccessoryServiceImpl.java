package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.CompatibilityEvaluator;
import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.receiver.SpecsResolverDelegator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AccessoryServiceImpl implements AccessoryService {
    private static final Logger LOG = LogManager.getLogger(AccessoryServiceImpl.class.getName());

    private final SpecsResolverDelegator resolverDelegator;
    private final CompatibilityEvaluator compatibilityEvaluator;

    @Autowired
    public AccessoryServiceImpl(SpecsResolverDelegator resolverDelegator, CompatibilityEvaluator compatibilityEvaluator) {
        this.resolverDelegator = resolverDelegator;
        this.compatibilityEvaluator = compatibilityEvaluator;
    }

    @Override
    public void doWork() {
        final long start = System.currentTimeMillis();

        CompletableFuture<Processor> processorFuture =
            resolverDelegator.receiveProcessorSpecifications("https://ek.ua/AMD-RYZEN-3-MATISSE.htm");
        CompletableFuture<Motherboard> motherboardFuture =
            resolverDelegator.receiveMotherboardSpecifications("https://ek.ua/ek-item.php?resolved_name_=ASUS-M5A78L-M-LX3&view_=tbl");
        CompletableFuture<Ram> ramFuture =
            resolverDelegator.receiveRamSpecifications("https://ek.ua/TEAM-GROUP-ELITE-SO-DIMM-DDR4.htm");

        CompletableFuture<Compatibility> motherboardProcessor =
            compatibilityEvaluator.checkMotherboardProcessorCompatibility(processorFuture, motherboardFuture);
        CompletableFuture<Compatibility> ramMotherboard =
            compatibilityEvaluator.checkMotherboardRamCompatibility(ramFuture, motherboardFuture);

        System.out.println(motherboardProcessor.join());
        System.out.println(ramMotherboard.join());

        final long stop = System.currentTimeMillis();
        LOG.info(stop - start);
    }

}
