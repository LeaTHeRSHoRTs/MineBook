package com.leathershorts.minebook.pages.components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class AppSidebar extends VBox {
    private static final double EXPANDED_WIDTH = 220;
    private static final double COLLAPSED_WIDTH = 55;

    private final VBox contents = new VBox(10);
    private final Button toggleButton = new Button("☰");

    private boolean collapsed = false;

    public AppSidebar() {
        setSpacing(10);
        setPadding(new Insets(10));
        setPrefWidth(EXPANDED_WIDTH);
        setMinWidth(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);

        toggleButton.setMaxWidth(Double.MAX_VALUE);
        toggleButton.setOnAction(event -> toggle());

        getChildren().addAll(toggleButton, contents);

        addItem("Home");
        addItem("Modpacks");
        addItem("Settings");
    }

    public void addItem(String text) {
        Button button = new Button(text);

        button.setMaxWidth(Double.MAX_VALUE);
        button.setUserData(text);

        contents.getChildren().add(button);
    }

    public void setCollapsed(boolean collapsed) {
        this.collapsed = collapsed;

        if (collapsed) {
            setPrefWidth(COLLAPSED_WIDTH);
            contents.setVisible(false);
            contents.setManaged(false);
            toggleButton.setText("»");
        } else {
            setPrefWidth(EXPANDED_WIDTH);
            contents.setVisible(true);
            contents.setManaged(true);
            toggleButton.setText("☰");
        }
    }

    public void toggle() {
        setCollapsed(!collapsed);
    }

    public boolean isCollapsed() {
        return collapsed;
    }
}