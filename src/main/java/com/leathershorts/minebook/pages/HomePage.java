package com.leathershorts.minebook.pages;

import com.leathershorts.minebook.ModpackRepository;
import com.leathershorts.minebook.game.structures.packs.Modpack;
import com.leathershorts.minebook.pages.components.AppSidebar;
import com.leathershorts.minebook.processors.FileProcessor;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.List;
import java.util.Objects;

public class HomePage extends BorderPane {
    private static boolean open = false;

    public HomePage(List<Modpack> modpacks) {
        setCenter(createContent(modpacks));
        open = true;
    }

    private VBox createContent(List<Modpack> modpacks) {
        if (!modpacks.isEmpty()) {
            VBox content = new VBox(10);
            content.setPadding(new Insets(20));

            Label heading = new Label("Modpacks");
            heading.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

            content.getChildren().add(heading);

            for (Modpack modpack : modpacks) {
                content.getChildren().add(createModpackItem(modpack));
            }

            return content;
        } else {
            VBox layout = new VBox(10);

            Label title = new Label("MineBook");
            title.getStyleClass().add("title");

            Button filePicker = new Button("Choose an .mrpack or .zip file to upload");
            Label selectedLabel = new Label("No file selected");

            filePicker.setOnAction(event -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Choose a Modpack");
                fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter(
                        "Modpack and ZIP files",
                        "*.mrpack",
                        "*.zip"
                    )
                );

                File selectedFile = fileChooser.showOpenDialog(this.getScene().getWindow());

                if (selectedFile != null) {
                    selectedLabel.setText("Selected: " + selectedFile.getName());
                    System.out.println(selectedFile.getAbsolutePath());

                    Modpack pack;
                    try {
                        pack = FileProcessor.processModpack(selectedFile);
                        ModpackRepository.save(pack);
                    } catch (InvalidObjectException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {

                    }
                }
            });

            filePicker.getStyleClass().add("file-picker");
            layout.getStyleClass().add("main");

            layout.getChildren().addAll(title, filePicker, selectedLabel);

            return layout;
        }
    }

    private VBox createModpackItem(Modpack modpack) {
        VBox item = new VBox(5);
        Button name = new Button(modpack.name());
        name.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        item.getChildren().add(name);
        return item;
    }
}
