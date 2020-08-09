package com.topov.accessorycompatibility.receiver.ekatalog;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.receiver.HardwareReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EkatalogHardwareReceiver implements HardwareReceiver {
    private static final String SUPPORTED_URL = "https://ek.ua/";

    private final EkatalogMotherboardReceiver ekatalogMotherboardReceiver;
    private final EkatalogProcessorReceiver ekatalogProcessorReceiver;
    private final EkatalogRamReceiver ekatalogRamReceiver;

    @Autowired
    public EkatalogHardwareReceiver(EkatalogMotherboardReceiver ekatalogMotherboardReceiver,
                                    EkatalogProcessorReceiver ekatalogProcessorReceiver,
                                    EkatalogRamReceiver ekatalogRamReceiver) {
        this.ekatalogMotherboardReceiver = ekatalogMotherboardReceiver;
        this.ekatalogProcessorReceiver = ekatalogProcessorReceiver;
        this.ekatalogRamReceiver = ekatalogRamReceiver;
    }

    @Override
    public Processor receiveProcessor(String processorUrl) {
        return ekatalogProcessorReceiver.receiveHardwareComponent(processorUrl);
    }

    @Override
    public Motherboard receiveMotherboard(String motherboardUrl) {
        return ekatalogMotherboardReceiver.receiveHardwareComponent(motherboardUrl);
    }

    @Override
    public Ram receiveRam(String ramUrl) {
        return ekatalogRamReceiver.receiveHardwareComponent(ramUrl);
    }

    @Override
    public boolean supports(String url) {
        return url.startsWith(SUPPORTED_URL);
    }
}
