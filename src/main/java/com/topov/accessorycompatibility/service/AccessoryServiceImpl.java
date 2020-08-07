package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.receiver.EkatalogSpecificationsReceiver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class AccessoryServiceImpl implements AccessoryService {
    private static final Logger LOG = LogManager.getLogger(AccessoryServiceImpl.class.getName());

    private final EkatalogSpecificationsReceiver specificationsReceiver;
    @Autowired
    public AccessoryServiceImpl(EkatalogSpecificationsReceiver specificationsReceiver) {
        this.specificationsReceiver = specificationsReceiver;
    }

    @Override
    public void doWork() {
        final long start = System.currentTimeMillis();
        CompletableFuture<Optional<Processor>> processor1 =
            specificationsReceiver.receiveProcessorSpecifications("AMD 3100");
        CompletableFuture<Optional<Motherboard>> motherboard =
            specificationsReceiver.receiveMotherboardSpecifications("ASROCK B450M PRO4 F");
        CompletableFuture<Optional<Processor>> processor3 =
            specificationsReceiver.receiveProcessorSpecifications("AMD 3100");
        CompletableFuture<Optional<Processor>> processor4 =
            specificationsReceiver.receiveProcessorSpecifications("AMD 3100");
        CompletableFuture<Optional<Processor>> processor5 =
            specificationsReceiver.receiveProcessorSpecifications("AMD 3100");
        Stream.of(processor1, motherboard, processor3, processor4, processor5)
              .map(CompletableFuture::join)
              .forEach(System.out::println);

        final long stop = System.currentTimeMillis();
        LOG.info(stop - start);
    }

}
