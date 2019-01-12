package com.twentythree.spaceclient.scene;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.game.GameManager;

import com.twentythree.spaceclient.controller.StageManager;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GameScene implements IScene {
    public Scene getScene(StageManager stageManager) {
        Pane pane = new Pane();
        GameManager manager = new GameManager(pane, stageManager.getLevelProvider());

        Label scoreTextLabel = new Label("Score: ");
        Label scoreAmountLabel = new Label();
        scoreAmountLabel.textProperty().bind(manager.getScoreProperty().asString());
        pane.getChildren().add(scoreTextLabel);
        pane.getChildren().add(scoreAmountLabel);
        scoreTextLabel.relocate(GUI.DEFAULT_INSET, GUI.DEFAULT_INSET);
        scoreAmountLabel.relocate(GUI.LARGE_INSET, GUI.DEFAULT_INSET);

        Label currentLevelTextLabel = new Label("Level: ");
        Label currentLevelAmountLabel = new Label();
        currentLevelAmountLabel.textProperty().bind(stageManager.getLevelProvider().getCurrentLevel().asString());
        pane.getChildren().add(currentLevelTextLabel);
        pane.getChildren().add(currentLevelAmountLabel);
        currentLevelTextLabel.relocate(GUI.WINDOW_WIDTH / 2.0 - GUI.DEFAULT_INSET, GUI.DEFAULT_INSET);
        currentLevelAmountLabel.relocate(GUI.WINDOW_WIDTH / 2.0 + GUI.DEFAULT_INSET, GUI.DEFAULT_INSET);

        Label healthTextLabel = new Label("Health: ");
        Label healthAmountLabel = new Label();
        healthAmountLabel.textProperty().bind(manager.getLocalPlayer().getHealthProperty().asString());
        pane.getChildren().add(healthTextLabel);
        pane.getChildren().add(healthAmountLabel);
        healthTextLabel.relocate(GUI.WINDOW_WIDTH - GUI.LARGE_INSET, GUI.DEFAULT_INSET);
        healthAmountLabel.relocate(GUI.WINDOW_WIDTH - GUI.DEFAULT_INSET, GUI.DEFAULT_INSET);

        Scene scene = new Scene(pane, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);

        // TODO Displaying the cursor for the demo, disable later
        // scene.setCursor(Cursor.NONE);
        scene.setOnMouseMoved(e -> {
            manager.getLocalPlayer().updatePosition(e.getX());
        });

        stageManager.setTitle("Space Shooter");

        return scene;
    }
}
