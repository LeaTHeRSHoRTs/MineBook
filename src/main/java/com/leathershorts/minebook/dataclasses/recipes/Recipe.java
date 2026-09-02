package com.leathershorts.minebook.dataclasses.recipes;

import com.leathershorts.minebook.dataclasses.ItemInfo;

import java.util.List;

public interface Recipe {
    List<ItemInfo> getIngredients();
    ItemInfo getResult();
    int getResultCount();
}
