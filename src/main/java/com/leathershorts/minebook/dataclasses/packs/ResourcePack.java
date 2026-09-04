package com.leathershorts.minebook.dataclasses.packs;

import com.leathershorts.minebook.dataclasses.Version;

public record ResourcePack(
    String name,
    Version version
) {}