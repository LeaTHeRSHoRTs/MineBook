package com.leathershorts.minebook.dataclasses;

public record Version(
    int major,
    int minor,
    int patch
) {
    public Version(int major, int minor) {
        this(major, minor, 0);
    }

    public static Version parse(String version) {
        String[] parts = version.split("\\.");
        int major = parts.length > 0 ? Integer.parseInt(parts[0]) : 0;
        int minor = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        int patch = parts.length > 2 ? Integer.parseInt(parts[2]) : 0;

        return new Version(major, minor, patch);
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

    @Override public String toString() {
        return this.getFullVersion();
    }

    private enum InternalVersion {
        MAJOR,
        MINOR,
        PATCH
    }
}