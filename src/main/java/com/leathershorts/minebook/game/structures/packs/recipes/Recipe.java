package com.leathershorts.minebook.game.structures.packs.recipes;

import com.leathershorts.minebook.game.structures.content.Item;

import java.util.List;

public record Recipe(
    Type type,
    List<Item> inputs,
    Item output,
    int count
) {
    public Recipe {
        int exact;

        switch (type) {
            case CRAFTING_SMALL -> {
                exact = 4;
            }
            case CRAFTING_BIG -> {
                exact = 9;
            }
            case SMELTING, BLASTING, SMOKING, STONECUTTING -> {
                exact = 1;
            }
            case SMITHING -> {
                exact = 3;
            }
            case CARTOGRAPHY -> {
                exact = 2;
            }
        }
    }

    public Recipe(Type type, List<Item> inputs, Item output) {
        this(type, inputs, output, 1);
    }
    public Recipe(List<Item> inputs, Item output, int count) {
        this(Type.CRAFTING_BIG, inputs, output, count);
    }
    public Recipe(List<Item> inputs, Item output) {
        this(Type.CRAFTING_BIG, inputs, output, 1);
    }

    List<Item> getIngredients() {
        return this.inputs;
    }
    Item getResult() {
        return this.output;
    }
    int getResultCount() {
        return this.count;
    }

    public enum Type {
        CRAFTING_SMALL,
        CRAFTING_BIG,
        SMELTING,
        BLASTING,
        SMOKING,
        STONECUTTING,
        SMITHING,
        CARTOGRAPHY,

    }
}
