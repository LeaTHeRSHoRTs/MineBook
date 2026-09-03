package com.leathershorts.minebook.dataclasses.packs;

import com.leathershorts.minebook.dataclasses.content.Tag;
import com.leathershorts.minebook.dataclasses.packs.recipes.Recipe;
import com.leathershorts.minebook.dataclasses.Version;

import java.util.List;

public record Datapack(
    String name,
    String desc,

    Version version,
    List<AdvancementInfo> advancements,
    List<LootTableInfo> lootTables,
    List<Recipe> recipes,
    List<Tag> blockItemTags,
    List<StructureInfo> structures,
    List<FunctionInfo> functions,
    List<Tag> functionTags,
    List<Tag> entityTypeTags,
    List<Tag> biomeTags,
    List<DimensionInfo> dimensions,
    List<BiomeInfo> biomes,
    List<DamageTypeInfo> damageTypes,
    List<EnchantmentInfo> enchantments,
    List<ItemModifierInfo> itemModifiers,
    List<WorldgenInfo> worldgen
) {}