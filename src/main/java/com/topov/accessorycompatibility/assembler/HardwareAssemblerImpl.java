package com.topov.accessorycompatibility.assembler;

import com.topov.accessorycompatibility.model.Motherboard;
import com.topov.accessorycompatibility.model.Processor;
import com.topov.accessorycompatibility.model.Ram;
import com.topov.accessorycompatibility.parser.Specifications;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import static com.topov.accessorycompatibility.parser.SpecificationGeneralizer.*;

@Service
public class HardwareAssemblerImpl implements HardwareAssembler {
    private static final Logger LOG = LogManager.getLogger(HardwareAssemblerImpl.class.getName());

    @Override
    public Processor assembleProcessor(Specifications specifications) {
        LOG.info("Assembling processor: " + Thread.currentThread().getName());
        final String socket = specifications.getStringValue(CPU_SOCKET_KEY);
        final String tdp = specifications.getNumberValue(CPU_TDP_KEY);
        final String frequency = specifications.getNumberValue(CPU_FREQUENCY_KEY);
        final String threads = specifications.getNumberValue(CPU_THREADS_KEY);
        final String cores = specifications.getNumberValue(CPU_CORES_KEY);
        return Processor.builder()
                        .socket(socket)
                        .heatRelease(Long.parseLong(tdp))
                        .cores(Integer.parseInt(cores))
                        .threads(Integer.parseInt(threads))
                        .frequency(Long.parseLong(frequency))
                        .build();
    }

    @Override
    public Motherboard assembleMotherboard(Specifications specifications) {
        LOG.info("Assembling motherboard: " + Thread.currentThread().getName());
        final String socket = specifications.getStringValue(MBD_SOCKET_KEY);
        final String chipset = specifications.getStringValue(MBD_CHIPSET_KEY);
        final String formFactor = specifications.getStringValue(MBD_FORM_FACTOR_KEY);
        final String maxRam = specifications.getNumberValue(MBD_MAX_RAM_KEY);
        final String ramType = specifications.getStringValue(MBD_RAM_TYPE);
        final String ramFormFactor = specifications.getStringValue(MBD_RAM_FROM_FACTOR_KEY);
        final String ramFrequency = specifications.getNumberValue(MBD_MAX_RAM_FREQUENCY_KEY);
        return Motherboard.builder()
                          .socket(socket)
                          .chipset(chipset)
                          .ramType(ramType)
                          .formFactor(formFactor)
                          .ramFormFactor(ramFormFactor)
                          .maxRam(Integer.parseInt(maxRam))
                          .ramFrequency(Integer.parseInt(ramFrequency))
                          .build();
    }

    @Override
    public Ram assembleRam(Specifications specifications) {
        LOG.info("Assembling ram: " + Thread.currentThread().getName());
        final String formFactor = specifications.getStringValue(RAM_FORM_FACTOR_KEY);
        final String frequency = specifications.getNumberValue(RAM_FREQUENCY_KEY);
        final String timings = specifications.getStringValue(RAM_TIMINGS_KEY);
        final String voltage = specifications.getNumberValue(RAM_VOLTAGE_KEY);
        final String type = specifications.getStringValue(RAM_TYPE_KEY);
        return Ram.builder()
                  .formFactor(formFactor)
                  .frequency(Integer.parseInt(frequency))
                  .timings(timings)
                  .voltage(Long.parseLong(voltage))
                  .type(type)
                  .build();
    }
}
