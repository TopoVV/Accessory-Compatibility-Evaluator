package com.topov.accessorycompatibility.hardware;

public abstract class Hardware {
    protected final String hardwareName;

    protected Hardware(String hardwareName) {
        this.hardwareName = hardwareName;
    }

    public String getHardwareName() {
        return this.hardwareName;
    }
}
