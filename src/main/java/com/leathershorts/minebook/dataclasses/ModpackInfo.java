package com.leathershorts.minebook.dataclasses;

import java.util.List;

public record ModpackInfo(
    String name,
    Version version,

    List<ModInfo> mods
) {}