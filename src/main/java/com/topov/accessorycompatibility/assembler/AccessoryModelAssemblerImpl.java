package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.parser.SpecificationsGeneralizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.topov.accessorycompatibility.parser.SpecificationsGeneralizer.*;

@Service
public class AccessoryModelAssemblerImpl implements AccessoryModelAssembler {
    private static final Logger LOG = LogManager.getLogger(AccessoryModelAssemblerImpl.class.getName());

    @Override
    public Processor assembleProcessor(Map<String, String> specifications) {
        LOG.info("Assembling processor: " + Thread.currentThread().getName());
        final String socket = specifications.getOrDefault(SpecificationsGeneralizer.SOCKET_KEY, "undefined");
        final String tdp = specifications.getOrDefault(SpecificationsGeneralizer.TDP_KEY, "-100");
        final String frequency = specifications.getOrDefault(SpecificationsGeneralizer.GHZ_KEY, "-100");
        final String threads = specifications.getOrDefault(SpecificationsGeneralizer.THREADS_KEY, "-100");
        final String cores = specifications.getOrDefault(SpecificationsGeneralizer.CORES_KEY, "-100");
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
        final String socket = specifications.getOrDefault(SpecificationsGeneralizer.SOCKET_KEY, "undefined");
        final String chipset = specifications.getOrDefault(SpecificationsGeneralizer.CHIPSET_KEY, "undefined");
        final String formFactor = specifications.getOrDefault(SpecificationsGeneralizer.MOTHERBOARD_FORM_FACTOR_KEY, "undefined");
        final String maxRam = specifications.getOrDefault(SpecificationsGeneralizer.MAX_RAM_KEY, "-100");
        final String ramFormFactor = specifications.getOrDefault(RAM_FORM_FACTOR_KEY, "undefined");
        final String ramFrequency = specifications.getOrDefault(SpecificationsGeneralizer.RAM_FREQUENCY_KEY, "-100");
        return Motherboard.builder()
                          .socket(socket)
                          .chipset(chipset)
                          .formFactor(formFactor)
                          .maxRam(Integer.parseInt(maxRam.replaceAll("\\D+", "")))
                          .ramFrequency(Integer.parseInt(ramFrequency.replaceAll("\\D+", "")))
                          .ramFormFactor(ramFormFactor)
                          .build();
    }

    @Override
    public Ram assembleRam(Map<String, String> generalized) {
        final String formFactor = generalized.getOrDefault(RAM_FORM_FACTOR_KEY, "undefined");
        final String frequency = generalized.getOrDefault(GHZ_KEY, "-100");
        final String timings = generalized.getOrDefault(RAM_TIMINGS_KEY, "undefined");
        final String voltage = generalized.getOrDefault(RAM_VOLTAGE_KEY, "-100");
        final String type = generalized.getOrDefault(RAM_TYPE_KEY, "-undefined");
        return Ram.builder()
                  .formFactor(formFactor)
                  .frequency(Integer.parseInt(frequency))
                  .timings(timings)
                  .voltage(Long.parseLong(voltage))
                  .type(type)
                  .build();


    }
}
