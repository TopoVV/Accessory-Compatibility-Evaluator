package com.topov.accessorycompatibility.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HardwareServiceImplTest {
    private final HardwareService service;

    @Autowired
    HardwareServiceImplTest(HardwareService service) {
        this.service = service;
    }


    @Test
    public void doWork() {
        service.doWork();
    }
}
