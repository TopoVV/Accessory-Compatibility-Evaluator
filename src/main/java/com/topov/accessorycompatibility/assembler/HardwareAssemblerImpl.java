package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.topov.accessorycompatibility.parser.SpecsGeneralizer.*;

@Service
public class HardwareAssemblerImpl implements HardwareAssembler {
    private static final Logger LOG = LogManager.getLogger(HardwareAssemblerImpl.class.getName());

    @Override
    public Processor assembleProcessor(Map<String, String> specifications) {
        LOG.info("Assembling processor: " + Thread.currentThread().getName());
        final String socket = specifications.getOrDefault(CPU_SOCKET_KEY, "undefined");
        final String tdp = specifications.getOrDefault(CPU_TDP_KEY, "-100");
        final String frequency = specifications.getOrDefault(CPU_FREQUENCY_KEY, "-100");
        final String threads = specifications.getOrDefault(CPU_THREADS_KEY, "-100");
        final String cores = specifications.getOrDefault(CPU_CORES_KEY, "-100");
        return Processor.builder()
                        .socket(socket)
                        .heatRelease(Long.parseLong(tdp.matches("[0-9]+") ? tdp : "-100"))
                        .cores(Integer.parseInt(cores.replaceAll("\\D+", "")))
                        .threads(Integer.parseInt(threads.replaceAll("\\D+", "")))
                        .frequency(Long.parseLong(frequency.replaceAll("\\D+", "")))
                        .build();
    }

    @Override
    public Motherboard assembleMotherboard(Map<String, String> specifications) {
        LOG.info("Assembling motherboard: " + Thread.currentThread().getName());
        final String socket = specifications.getOrDefault(MBD_SOCKET_KEY, "undefined");
        final String chipset = specifications.getOrDefault(MBD_CHIPSET_KEY, "undefined");
        final String formFactor = specifications.getOrDefault(MBD_FORM_FACTOR_KEY, "undefined");
        final String maxRam = specifications.getOrDefault(MBD_MAX_RAM_KEY, "-100");
        final String ramType = specifications.getOrDefault(MBD_RAM_TYPE, "undefined");
        final String ramFormFactor = specifications.getOrDefault(MBD_RAM_FROM_FACTOR_KEY, "undefined");
        final String ramFrequency = specifications.getOrDefault(MBD_MAX_RAM_FREQUENCY_KEY, "-100");
        return Motherboard.builder()
                          .socket(socket)
                          .chipset(chipset)
                          .ramType(ramType)
                          .formFactor(formFactor)
                          .ramFormFactor(ramFormFactor)
                          .maxRam(Integer.parseInt(maxRam.replaceAll("\\D+", "")))
                          .ramFrequency(Integer.parseInt(ramFrequency.replaceAll("\\D+", "")))
                          .build();
    }

    @Override
    public Ram assembleRam(Map<String, String> generalized) {
        LOG.info("Assembling ram: " + Thread.currentThread().getName());
        final String formFactor = generalized.getOrDefault(RAM_FORM_FACTOR_KEY, "undefined");
        final String frequency = generalized.getOrDefault(RAM_FREQUENCY_KEY, "-100");
        final String timings = generalized.getOrDefault(RAM_TIMINGS_KEY, "undefined");
        final String voltage = generalized.getOrDefault(RAM_VOLTAGE_KEY, "-100");
        final String type = generalized.getOrDefault(RAM_TYPE_KEY, "-undefined");
        return Ram.builder()
                  .formFactor(formFactor)
                  .frequency(Integer.parseInt(frequency.replaceAll("\\D+", "")))
                  .timings(timings)
                  .voltage(Long.parseLong(voltage.replaceAll("\\D+", "")))
                  .type(type)
                  .build();
    }
}
