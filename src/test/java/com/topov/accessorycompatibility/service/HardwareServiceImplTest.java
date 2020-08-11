package com.topov.accessorycompatibility.service;

import com.topov.accessorycompatibility.compatibility.evaluation.CompatibilityResult;
import com.topov.accessorycompatibility.dto.CompatibilityResultDto;
import com.topov.accessorycompatibility.dto.request.HardwareSpecificationSources;
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
        when(sources.getCpuUrl()).thenReturn("https://ek.ua/AMD-RYZEN-3-MATISSE.htm");
        when(sources.getPcbUrl()).thenReturn("https://ek.ua/ek-item.php?idg_=1688473&view_=tbl");
        when(sources.getRamUrl()).thenReturn("https://ek.ua/TEAM-GROUP-ELITE-SO-DIMM-DDR4.htm");
        final List<CompatibilityResultDto> compatibilityResults = service.evaluateHardwareCompatibility(sources);
        compatibilityResults.forEach(System.out::println);
    }
}
