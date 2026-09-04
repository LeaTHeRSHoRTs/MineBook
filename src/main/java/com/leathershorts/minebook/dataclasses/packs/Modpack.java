package com.leathershorts.minebook.dataclasses.packs;

import com.leathershorts.minebook.dataclasses.Version;

import java.util.List;

public record Modpack(
    String name,
    String description,
    Version version,
    Loader modLoader,
    List<Mod> mods,
    List<Datapack> datapacks,
    List<ResourcePack> resourcePacks
) {}