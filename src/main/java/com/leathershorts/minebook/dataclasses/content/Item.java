package com.leathershorts.minebook.dataclasses.content;

public record Item(
    String namespace,
    String name
) {
    String id() {
        return name + ':' + namespace;
    }
}