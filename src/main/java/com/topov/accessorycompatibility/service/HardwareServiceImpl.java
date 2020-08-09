package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.dto.response.Compatibility;
import com.topov.accessorycompatibility.hardware.compatibility.CompatiblePair;
import com.topov.accessorycompatibility.hardware.components.Cpu;
import com.topov.accessorycompatibility.hardware.components.Pcb;
import com.topov.accessorycompatibility.hardware.components.Ram;
import com.topov.accessorycompatibility.receiver.HadrwareReceiverDelegator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class HardwareServiceImpl implements HardwareService {
    private static final Logger LOG = LogManager.getLogger(HardwareServiceImpl.class.getName());

    private final HadrwareReceiverDelegator receiverDelegator;
    private final CompatibilityService compatibilityService;

    @Autowired
    public HardwareServiceImpl(HadrwareReceiverDelegator receiverDelegator, CompatibilityService compatibilityService) {
        this.receiverDelegator = receiverDelegator;
        this.compatibilityService = compatibilityService;
    }

    @Override
    public void doWork() {
        final String cpuUrl = "https://ek.ua/AMD-RYZEN-3-MATISSE.htm";
        final String pcbUrl = "https://ek.ua/ek-item.php?resolved_name_=ASUS-M5A78L-M-LX3&view_=tbl";
        final String ramUrl = "https://ek.ua/TEAM-GROUP-ELITE-SO-DIMM-DDR4.htm";
        CompletableFuture<Cpu> cpu = receiverDelegator.receiveProcessor(cpuUrl);
        CompletableFuture<Pcb> pcb = receiverDelegator.receiveMotherboard(pcbUrl);
        CompletableFuture<Ram> ram = receiverDelegator.receiveRam(ramUrl);


        final CompletableFuture<CompatiblePair> pcbCpu = pcb.thenCombine(cpu, CompatiblePair::pcbCpuPair);
        final CompletableFuture<CompatiblePair> pcbRam = pcb.thenCombine(ram, CompatiblePair::pcbRamPair);
        final CompletableFuture<Compatibility> pcbCpuCompatibility = compatibilityService.evaluateCompatibility(pcbCpu);
        final CompletableFuture<Compatibility> pcbRamCompatibility = compatibilityService.evaluateCompatibility(pcbRam);

        System.out.println(pcbCpuCompatibility.join());
        System.out.println(pcbRamCompatibility.join());
    }

}
