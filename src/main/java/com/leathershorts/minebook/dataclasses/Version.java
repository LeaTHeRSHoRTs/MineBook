package com.leathershorts.minebook.dataclasses;

public record Version(
    int major,
    int minor,
    int patch
) {
    public Version(int major, int minor) {
        this(major, minor, 0);
    }

    private String getVersion(InternalVersion type) {
        return switch (type) {
            case MAJOR -> "%d".formatted(this.major);
            case MINOR -> "%d.%d".formatted(this.major, this.minor);
            case PATCH -> "%d.%d.%d".formatted(this.major, this.minor, this.patch);
        };
    }

    public String getVersion() {
        return this.patch == 0 ? this.getVersion(InternalVersion.MINOR) : this.getVersion(InternalVersion.PATCH);
    }

    public String getFullVersion() {
        return this.getVersion(InternalVersion.PATCH);
    }

    private enum InternalVersion {
        MAJOR,
        MINOR,
        PATCH
    }
}