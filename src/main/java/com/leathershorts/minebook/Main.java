package com.leathershorts.minebook;

import com.google.gson.Gson;
import com.leathershorts.minebook.dataclasses.packs.Modpack;
import com.leathershorts.minebook.processors.FileProcessor;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.Objects;

public class Main extends Application {
    public static final Gson GSON = new Gson();
    private final String stylesheet = Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm();

    @Override
    public void start(Stage stage) {
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

            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                selectedLabel.setText("Selected: " + selectedFile.getName());
                System.out.println(selectedFile.getAbsolutePath());

                Modpack pack;

                try {
                    pack = FileProcessor.processModpack(selectedFile);
                } catch (InvalidObjectException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {

                }
            }
        });
        filePicker.getStyleClass().add("file-picker");

        VBox layout = new VBox(title, filePicker, selectedLabel);
        layout.getStyleClass().add("main");
        Scene scene = new Scene(layout, 450, 200);
        scene.getStylesheets().add(stylesheet);

        stage.setTitle("MineBook");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}