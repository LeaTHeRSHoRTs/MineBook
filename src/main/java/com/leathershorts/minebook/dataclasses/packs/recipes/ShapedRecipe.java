package com.leathershorts.minebook.dataclasses.packs.recipes;

import com.leathershorts.minebook.dataclasses.content.Item;

import java.util.List;

public record ShapedRecipe(Item result, Item[] ingredients, int count) implements Recipe {
    public ShapedRecipe {
        if (ingredients.length != 9) {
            throw new IllegalArgumentException("Shaped recipe matrix must be exactly 9 slots.");
        }
    }

    @Override
    public List<Item> getIngredients() {
        return List.of(ingredients);
    }

    @Override
    public Item getResult() {
        return null;
    }

    @Override
    public int getResultCount() {
        return this.count;
    }
}
