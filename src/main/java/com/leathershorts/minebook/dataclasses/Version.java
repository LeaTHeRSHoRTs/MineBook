package com.leathershorts.minebook.dataclasses;

public record Version(
    int major,
    int minor,
    int patch
) {
    @Override
    public int major() {
        return major;
    }

    @Override
    public int patch() {
        return patch;
    }

    @Override
    public int minor() {
        return minor;
    }
}