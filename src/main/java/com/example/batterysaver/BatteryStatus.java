package com.example.batterysaver;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class BatteryStatus {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a");

    private final int level;
    private final boolean charging;
    private final boolean available;
    private final LocalDateTime checkedAt;

    public BatteryStatus(int level, boolean charging, boolean available, LocalDateTime checkedAt) {
        this.level = Math.max(0, Math.min(100, level));
        this.charging = charging;
        this.available = available;
        this.checkedAt = checkedAt;
    }

    public static BatteryStatus unavailable() {
        return new BatteryStatus(100, true, false, LocalDateTime.now());
    }

    public int level() {
        return level;
    }

    public boolean charging() {
        return charging;
    }

    public boolean available() {
        return available;
    }

    public String checkedAtText() {
        return checkedAt.format(FORMATTER);
    }

    public String powerStateText() {
        if (!available) {
            return "Battery sensor unavailable";
        }
        return charging ? "Plugged in and charging" : "Running on battery";
    }
}
