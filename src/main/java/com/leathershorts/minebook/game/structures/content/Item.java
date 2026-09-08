package com.leathershorts.minebook.game.structures.content;

public record Item(
    String namespace,
    String name
) {
    String id() {
        return name + ':' + namespace;
    }
}