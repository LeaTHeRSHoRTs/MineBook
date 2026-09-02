package com.leathershorts.minebook.dataclasses;

import com.leathershorts.minebook.dataclasses.recipes.Recipe;
import com.leathershorts.minebook.enums.Environment;

import java.util.List;

public record ModInfo(
    String name,
    String id,
    Version version,
    Version minecraftVersion,
    String license,
    Environment environment,

    List<ModInfo> dependencies,
    List<String> authors,

    List<ItemInfo> items,
    List<BlockInfo> blocks,
    List<MobInfo> mobs,
    List<Recipe> recipes
) {}