package com.example.batterysaver;

import java.time.LocalDateTime;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.PowerSource;

public final class BatteryMonitor {
    private final SystemInfo systemInfo = new SystemInfo();

    public BatteryStatus readStatus() {
        List<PowerSource> sources = systemInfo.getHardware().getPowerSources();
        if (sources.isEmpty()) {
            return BatteryStatus.unavailable();
        }

        PowerSource source = sources.get(0);
        int level = (int) Math.round(source.getRemainingCapacityPercent() * 100);
        return new BatteryStatus(level, source.isCharging(), true, LocalDateTime.now());
    }
}
