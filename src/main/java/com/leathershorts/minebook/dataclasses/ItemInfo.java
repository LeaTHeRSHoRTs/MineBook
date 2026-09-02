package com.leathershorts.minebook.dataclasses;

public record ItemInfo(
    String namespace,
    String name
) {
    String id() {
        return name + ':' + namespace;
    }
}