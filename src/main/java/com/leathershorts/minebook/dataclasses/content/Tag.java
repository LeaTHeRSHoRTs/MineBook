package com.leathershorts.minebook.dataclasses.content;

import com.leathershorts.minebook.enums.TagType;

import java.util.List;

public record Tag(
    String namespace,
    String name,
    TagType type,
    List<Item> items
) {
    public Tag {
        items = List.copyOf(items);
    }
    public String id() {
        return namespace + ":" + name;
    }
}