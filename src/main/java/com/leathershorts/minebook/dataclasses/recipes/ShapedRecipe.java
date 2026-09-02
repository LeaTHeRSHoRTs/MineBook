package com.leathershorts.minebook.dataclasses.recipes;

import com.leathershorts.minebook.dataclasses.ItemInfo;

import java.util.Arrays;
import java.util.List;

public record ShapedRecipe(ItemInfo result, ItemInfo[] ingredients, int count) implements Recipe {
    public ShapedRecipe {
        if (ingredients.length != 9) {
            throw new IllegalArgumentException("Shaped recipe matrix must be exactly 9 slots.");
        }
    }

    @Override
    public List<ItemInfo> getIngredients() {
        return List.of(ingredients);
    }

    @Override
    public ItemInfo getResult() {
        return null;
    }

    @Override
    public int getResultCount() {
        return this.count;
    }
}
