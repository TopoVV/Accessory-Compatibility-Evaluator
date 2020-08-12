package com.topov.accessorycompatibility.dto.request;

import com.topov.accessorycompatibility.hardware.sources.CpuSource;
import com.topov.accessorycompatibility.hardware.sources.PcbSource;
import com.topov.accessorycompatibility.hardware.sources.RamSource;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class HardwareSpecificationSources {
    private String cpuUrl;
    private String pcbUrl;
    private String ramUrl;

    public CpuSource getCpuSource() {
        return new CpuSource(cpuUrl);
    }

    public PcbSource getPcbSource() {
        return new PcbSource(pcbUrl);
    }

    public RamSource getRamSource() {
        return new RamSource(ramUrl);
    }
}
