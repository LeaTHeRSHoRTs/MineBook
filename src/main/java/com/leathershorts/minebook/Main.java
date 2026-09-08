package com.leathershorts.minebook;

import com.google.gson.Gson;
import com.leathershorts.minebook.pages.HomePage;
import com.leathershorts.minebook.pages.components.AppSidebar;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {
    private StackPane stack;
    private BorderPane currentPage;

    public static final Gson GSON = new Gson();
    private final String STYLESHEET = Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm();
    private static final Screen PRIMARY = Screen.getPrimary();

    @Override
    public void start(Stage stage) {
        stack = new StackPane();

        HomePage home = new HomePage(ModpackRepository.fetch());
        AppSidebar sidebar = new AppSidebar(
            new AppSidebar.Item("Home", this::showHome),
            new AppSidebar.Item("Settings", this::showSettings)
        );

        this.showHome();
        stack.getChildren().add(sidebar);
        stack.getStyleClass().add("root");
        StackPane.setAlignment(home, Pos.CENTER);
        StackPane.setAlignment(sidebar, Pos.TOP_CENTER);

        Rectangle2D bounds = PRIMARY.getVisualBounds();
        double width = bounds.getWidth() / 2;
        double height = bounds.getHeight() / 2;

        Scene scene = new Scene(stack, width, height);
        scene.getStylesheets().add(STYLESHEET);
        stage.setTitle("MineBook");
        stage.setScene(scene);
        stage.show();
    }

    /** Shows the homepage to the user via the stack */
    private void showHome() {
        HomePage home = new HomePage(ModpackRepository.fetch());
        StackPane.setAlignment(home, Pos.CENTER);
        stack.getChildren().add(home);
    }

    /** Shows the settings page to the user via the stack */
    private void showSettings() {

    }

    public static void main(String[] args) {
        launch(args);
    }
}