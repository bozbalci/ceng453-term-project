package com.twentythree.spaceclient.scene;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.controller.StageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MainMenuScene {
    private GridPane pane;
    private Scene scene;

    private MainMenuScene(GridPane pane, Scene scene) {
        this.pane = pane;
        this.scene = scene;
    }

    private static MainMenuScene instance;

    public static MainMenuScene create(StageManager stageManager) {
        GridPane pane = new GridPane();

        pane.setAlignment(Pos.CENTER);
        pane.setHgap(GUI.DEFAULT_GAP);
        pane.setVgap(GUI.DEFAULT_GAP);
        pane.setPadding(new Insets(GUI.DEFAULT_INSET, GUI.DEFAULT_INSET, GUI.DEFAULT_INSET, GUI.DEFAULT_INSET));

        Button newGame = new Button("New Game");
        newGame.setOnAction(e -> {
            stageManager.getLevelProvider().reset();
            stageManager.toScene(SceneType.GAME_SCENE);
        });
        pane.add(newGame, 1, 1);

        Button quit = new Button("Quit Game");
        quit.setOnAction(e -> {
            System.exit(0);
        });
        pane.add(quit, 1, 2);

        instance = new MainMenuScene(pane, new Scene(pane, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT));

        stageManager.setTitle("Space Shooter: Main Menu");

        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}
