package com.leathershorts.minebook.pages;

import com.leathershorts.minebook.game.structures.packs.Modpack;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ModpackPage extends BorderPane {
    private final VBox content = new VBox(10);
    private final VBox sidebar = new VBox(10);

    public ModpackPage(Modpack modpack) {
        buildContent(modpack);
        buildSidebar(modpack);

        setCenter(content);
        setRight(sidebar);

        updateSidebarVisibility();
    }

    private void buildContent(Modpack modpack) {
        content.setPadding(new Insets(20));

        Label title = new Label(modpack.name());
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        content.getChildren().add(title);

        if (modpack.description() != null &&
            !modpack.description().isBlank()) {

            Label description = new Label(modpack.description());
            description.setWrapText(true);

            content.getChildren().add(description);
        }
    }

    private void buildSidebar(Modpack modpack) {
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(250);

        addDetail("Version", modpack.version());
        addDetail("Loader", modpack.modLoader());
    }

    private void addDetail(String label, Object value) {
        if (value == null) return;

        String text = value.toString();
        if (text.isBlank()) return;

        sidebar.getChildren().add(new Label(label + ": " + text));
    }

    private void updateSidebarVisibility() {
        boolean hasDetails = !sidebar.getChildren().isEmpty();

        sidebar.setVisible(hasDetails);
        sidebar.setManaged(hasDetails);
    }
}
