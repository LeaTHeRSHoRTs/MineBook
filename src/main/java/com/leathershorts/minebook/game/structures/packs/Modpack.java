package com.leathershorts.minebook.game.structures.packs;

import com.leathershorts.minebook.game.Version;

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