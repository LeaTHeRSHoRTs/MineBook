package com.leathershorts.minebook.game.structures.packs;

import com.leathershorts.minebook.game.structures.content.Block;
import com.leathershorts.minebook.game.structures.content.Item;
import com.leathershorts.minebook.game.structures.content.Mob;
import com.leathershorts.minebook.game.structures.packs.recipes.Recipe;
import com.leathershorts.minebook.game.Version;
import com.leathershorts.minebook.enums.Environment;

import java.util.List;

public record Mod(
    String name,
    String id,
    Version version,
    Version minecraftVersion,
    String license,
    Environment environment,

    List<Mod> dependencies,
    List<String> authors,

    List<Item> items,
    List<Block> blocks,
    List<Mob> mobs,
    List<Recipe> recipes
) {}