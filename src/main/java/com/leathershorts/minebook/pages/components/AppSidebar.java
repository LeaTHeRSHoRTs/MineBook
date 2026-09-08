package com.leathershorts.minebook.pages.components;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.util.ArrayList;
import java.util.List;

public class AppSidebar extends HBox {
    public AppSidebar(Item... pageSetups) {
        List<Button> buttons = new ArrayList<>();

        for (Item setup : pageSetups) {
            Button button = new Button(setup.name);
            button.setOnAction(a -> setup.action.run());
            buttons.add(button);
        }

        this.setMaxHeight(Region.USE_PREF_SIZE);
        this.getStyleClass().add("sidebar");

        getChildren().addAll(buttons);
        getChildren().forEach(i -> i.getStyleClass().add("sidebar-item"));
    }

    public record Item(String name, Runnable action) {}
}