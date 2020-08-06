package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.assembler.AccessoryModelAssembler;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.receiver.EkatalogSpecificationsReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AccessoryServiceImpl implements AccessoryService {
    private static final Logger LOG = LogManager.getLogger(AccessoryServiceImpl.class.getName());

    private final EkatalogSpecificationsReceiver specificationsReceiver;
    private final AccessoryModelAssembler accessoryAssembler;

    @Autowired
    public AccessoryServiceImpl(EkatalogSpecificationsReceiver specificationsReceiver, AccessoryModelAssembler accessoryAssembler) {
        this.specificationsReceiver = specificationsReceiver;
        this.accessoryAssembler = accessoryAssembler;
    }

    @Override
    public void doWork() {
        LOG.info("Starting");
        final long start = System.currentTimeMillis();
        final CompletableFuture<Processor> join1 =
            specificationsReceiver.receiveProcessorSpecifications("AMD-3600-BOX")
                                  .thenApply(accessoryAssembler::assembleProcessor)
                                  .exceptionally(throwable -> {
                                      LOG.info(throwable);
                                      return null;
                                  });
        final CompletableFuture<Processor> join2 =
            specificationsReceiver.receiveProcessorSpecifications("AMD-3600-BOX")
                                  .thenApply(accessoryAssembler::assembleProcessor)
                                  .exceptionally(throwable -> {
                                      LOG.info(throwable);
                                      return null;
                                  });
        final CompletableFuture<Processor> join3 =
            specificationsReceiver.receiveProcessorSpecifications("AMD-3600-BOX")
                                  .thenApply(accessoryAssembler::assembleProcessor)
                                  .exceptionally(throwable -> {
                                      LOG.info(throwable);
                                      return null;
                                  });
        final CompletableFuture<Processor> join4 =
            specificationsReceiver.receiveProcessorSpecifications("AMD-36wq0-BOX")
                                  .thenApply(accessoryAssembler::assembleProcessor)
                                  .exceptionally(throwable -> {
                                      LOG.error(throwable);
                                      return null;
                                  });
        final CompletableFuture<Processor> join5 =
            specificationsReceiver.receiveProcessorSpecifications("AMD-3600-BOX")
                                  .thenApply(accessoryAssembler::assembleProcessor)
                                  .exceptionally(throwable -> {
                                      LOG.info(throwable);
                                      return null;
                                  });
        System.out.println(join1.join());
        System.out.println(join2.join());
        System.out.println(join3.join());
        System.out.println(join4.join());
        System.out.println(join5.join());
        final long stop = System.currentTimeMillis();
        LOG.info(stop - start);
    }
}
