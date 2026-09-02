package com.leathershorts.minebook.dataclasses.recipes;

import com.leathershorts.minebook.dataclasses.ItemInfo;

import java.util.List;
import java.util.stream.Stream;

public record ShapelessRecipe(ItemInfo result, List<ItemInfo> ingredients, int resultCount) implements Recipe {
    public ShapelessRecipe {
        if (ingredients.size() > 9) {
            throw new IllegalArgumentException("Cannot create a recipe with more than 9 ingredients");
        }
    }

    public ShapelessRecipe(ItemInfo result, ItemInfo item, int count, int resultCount) {
        this(result, Stream.generate(() -> item).limit(count).toList(), resultCount);
    }

    @Override
    public List<ItemInfo> getIngredients() {
        return ingredients;
    }

    @Override
    public ItemInfo getResult() {
        return result;
    }

    @Override
    public int getResultCount() {
        return resultCount;
    }
}
