package com.leathershorts.minebook.dataclasses.packs.recipes;

import com.leathershorts.minebook.dataclasses.content.Item;

import java.util.List;

public interface Recipe {
    List<Item> getIngredients();
    Item getResult();
    int getResultCount();
}
