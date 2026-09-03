package com.leathershorts.minebook.dataclasses.packs.recipes;

import com.leathershorts.minebook.dataclasses.content.Item;

import java.util.List;
import java.util.stream.Stream;

public record ShapelessRecipe(Item result, List<Item> ingredients, int resultCount) implements Recipe {
    public ShapelessRecipe {
        if (ingredients.size() > 9) {
            throw new IllegalArgumentException("Cannot create a recipe with more than 9 ingredients");
        }
    }

    public ShapelessRecipe(Item result, Item item, int count, int resultCount) {
        this(result, Stream.generate(() -> item).limit(count).toList(), resultCount);
    }

    @Override
    public List<Item> getIngredients() {
        return ingredients;
    }

    @Override
    public Item getResult() {
        return result;
    }

    @Override
    public int getResultCount() {
        return resultCount;
    }
}
