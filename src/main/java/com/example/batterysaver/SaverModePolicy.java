package com.example.batterysaver;

public final class SaverModePolicy {
    private static final int CRITICAL_LEVEL = 20;
    private static final int LOW_LEVEL = 35;

    public PowerProfile chooseProfile(BatteryStatus status) {
        if (!status.available()) {
            return new PowerProfile(false, 80, 15, true, true, "Balanced", "Battery details are not exposed by this device.");
        }

        if (status.charging()) {
            return new PowerProfile(false, 90, 10, true, true, "Charging", "Power saver is paused while the device is charging.");
        }

        if (status.level() <= CRITICAL_LEVEL) {
            return new PowerProfile(true, 35, 30, false, false, "Ultra Saver", "Battery is at or below 20%, so strict saving is active.");
        }

        if (status.level() <= LOW_LEVEL) {
            return new PowerProfile(true, 55, 20, false, true, "Smart Saver", "Battery is low, so efficient settings are active.");
        }

        return new PowerProfile(false, 85, 10, true, true, "Balanced", "Battery level is healthy.");
    }
}
