package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import com.topov.accessorycompatibility.dto.request.HardwareSpecificationSources;
import com.topov.accessorycompatibility.hardware.sources.CpuSource;
import com.topov.accessorycompatibility.hardware.sources.PcbSource;
import com.topov.accessorycompatibility.hardware.sources.RamSource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class HardwareServiceImplTest {
    private final HardwareService service;

    @Autowired
    HardwareServiceImplTest(HardwareService service) {
        this.service = service;
    }


    @Test
    public void doWork() {
        final HardwareSpecificationSources sources = mock(HardwareSpecificationSources.class);
        when(sources.getCpuSource()).thenReturn(new CpuSource("https://ek.ua/AMD-RYZEN-3-MATISSE.htm"));
        when(sources.getPcbSource()).thenReturn(new PcbSource("https://ek.ua/ek-item.php?resolved_name_=ASUS-M5A78L-M-LX3&view_=tbl"));
        when(sources.getRamSource()).thenReturn(new RamSource("https://ek.ua/TEAM-GROUP-ELITE-SO-DIMM-DDR4.htm"));
        final List<CompatibilityResultDto> compatibilityResults = service.evaluateHardwareCompatibility(sources);
        compatibilityResults.forEach(System.out::println);
    }
}
