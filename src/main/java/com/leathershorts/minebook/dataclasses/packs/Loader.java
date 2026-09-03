package com.leathershorts.minebook.dataclasses.packs;

import com.leathershorts.minebook.dataclasses.Version;

public record Loader(
    String name,
    Version version
) {}