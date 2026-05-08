package com.example.batterysaver;

public final class BatterySaverSnapshot {
    private final BatteryStatus status;
    private final PowerProfile profile;

    public BatterySaverSnapshot(BatteryStatus status, PowerProfile profile) {
        this.status = status;
        this.profile = profile;
    }

    public BatteryStatus status() {
        return status;
    }

    public PowerProfile profile() {
        return profile;
    }
}
