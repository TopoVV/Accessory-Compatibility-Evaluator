package com.topov.accessorycompatibility.hardware.components;

import com.topov.accessorycompatibility.hardware.Hardware;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public class Cpu extends Hardware {
    private final String socket;
    private final Integer cores;
    private final Integer threads;
    private final Long frequency;
    private final Long heatRelease;

    @Builder
    public Cpu(String socket, Integer cores, Integer threads, Long frequency, Long heatRelease) {
        super("processor");
        this.socket = socket;
        this.cores = cores;
        this.threads = threads;
        this.frequency = frequency;
        this.heatRelease = heatRelease;
    }
}
