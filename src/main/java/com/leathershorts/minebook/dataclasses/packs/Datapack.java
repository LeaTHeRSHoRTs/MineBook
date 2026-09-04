package com.leathershorts.minebook.dataclasses.packs;

import com.leathershorts.minebook.dataclasses.content.*;
import com.leathershorts.minebook.dataclasses.packs.recipes.Recipe;
import com.leathershorts.minebook.dataclasses.Version;

import java.util.List;

public record Datapack(
    String name,
    String desc,

    Version version,
    List<Advancement> advancements,
    List<LootTable> lootTables,
    List<Recipe> recipes,
    List<Tag> blockItemTags,
    List<Structure> structures,
    List<Function> functions,
    List<Tag> functionTags,
    List<Tag> entityTypeTags,
    List<Tag> biomeTags,
    List<Dimension> dimensions,
    List<Biome> biomes,
    List<DamageType> damageTypes,
    List<Enchantment> enchantments,
    List<ItemModifier> itemModifiers,
    List<Worldgen> worldgen
) {}