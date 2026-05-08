package com.example.batterysaver;

import org.springframework.stereotype.Service;

@Service
public class BatteryMonitor {

    private int batteryLevel = 100; // Simulated battery level
    private boolean saverMode = false;

    public int getBatteryLevel() {
        // In real scenario, this would read from system
        // For demo, simulate decreasing
        batteryLevel = Math.max(0, batteryLevel - (int)(Math.random() * 5));
        return batteryLevel;
    }

    public boolean isSaverMode() {
        return saverMode;
    }

    public void activateSaverMode() {
        saverMode = true;
    }

    public void deactivateSaverMode() {
        saverMode = false;
    }

    public void adjustSettings() {
        if (getBatteryLevel() < 20 && !saverMode) {
            activateSaverMode();
        } else if (getBatteryLevel() > 50 && saverMode) {
            deactivateSaverMode();
        }
    }

    public String getStatus() {
        adjustSettings();
        return "Battery Level: " + batteryLevel + "%, Saver Mode: " + (saverMode ? "Active" : "Inactive");
    }
}