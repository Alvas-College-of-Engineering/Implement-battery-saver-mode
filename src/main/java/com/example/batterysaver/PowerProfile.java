package com.example.batterysaver;

public final class PowerProfile {
    private final boolean saverMode;
    private final int screenBrightness;
    private final int refreshSeconds;
    private final boolean backgroundSync;
    private final boolean animations;
    private final String modeName;
    private final String reason;

    public PowerProfile(
            boolean saverMode,
            int screenBrightness,
            int refreshSeconds,
            boolean backgroundSync,
            boolean animations,
            String modeName,
            String reason) {
        this.saverMode = saverMode;
        this.screenBrightness = screenBrightness;
        this.refreshSeconds = refreshSeconds;
        this.backgroundSync = backgroundSync;
        this.animations = animations;
        this.modeName = modeName;
        this.reason = reason;
    }

    public boolean saverMode() {
        return saverMode;
    }

    public int screenBrightness() {
        return screenBrightness;
    }

    public int refreshSeconds() {
        return refreshSeconds;
    }

    public boolean backgroundSync() {
        return backgroundSync;
    }

    public boolean animations() {
        return animations;
    }

    public String modeName() {
        return modeName;
    }

    public String reason() {
        return reason;
    }
}
