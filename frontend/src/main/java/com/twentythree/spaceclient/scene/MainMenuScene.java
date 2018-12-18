package com.twentythree.spaceclient.scene;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.controller.StageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MainMenuScene implements IScene {
    public Scene getScene(StageManager stageManager) {
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

        Button leaderboard = new Button("Leaderboard");
        leaderboard.setOnAction(e -> {
            stageManager.toScene(SceneType.LEADERBOARD_SCENE);
        });
        pane.add(leaderboard, 1, 2);

        Button quit = new Button("Quit Game");
        quit.setOnAction(e -> {
            System.exit(0);
        });
        pane.add(quit, 1, 3);

        stageManager.setTitle("Space Shooter: Main Menu");

        return new Scene(pane, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
    }
}
