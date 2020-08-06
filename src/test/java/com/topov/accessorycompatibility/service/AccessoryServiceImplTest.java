package com.topov.accessorycompatibility.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccessoryServiceImplTest {
    private final AccessoryService service;

    @Autowired
    AccessoryServiceImplTest(AccessoryService service) {
        this.service = service;
    }


    @Test
    public void doWork() {
        service.doWork();
    }
}
