package com.example.batterysaver;

public final class BatterySaverService {
    private final BatteryMonitor monitor;
    private final SaverModePolicy policy;

    public BatterySaverService(BatteryMonitor monitor, SaverModePolicy policy) {
        this.monitor = monitor;
        this.policy = policy;
    }

    public BatterySaverSnapshot currentSnapshot() {
        BatteryStatus status = monitor.readStatus();
        PowerProfile profile = policy.chooseProfile(status);
        return new BatterySaverSnapshot(status, profile);
    }
}
