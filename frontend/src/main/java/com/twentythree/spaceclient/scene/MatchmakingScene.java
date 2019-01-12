package com.twentythree.spaceclient.scene;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.Network;
import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.controller.StageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.HashMap;

public class MatchmakingScene implements IScene {
    public Scene getScene(StageManager stageManager) {
        GridPane pane = new GridPane();

        pane.setAlignment(Pos.CENTER);
        pane.setHgap(GUI.DEFAULT_GAP);
        pane.setVgap(GUI.DEFAULT_GAP);
        pane.setPadding(new Insets(GUI.DEFAULT_INSET, GUI.DEFAULT_INSET, GUI.DEFAULT_INSET, GUI.DEFAULT_INSET));

        Label awaitingMessage = new Label("You have completed the single player levels!");
        pane.add(awaitingMessage, 1, 1);

        Label yourScoreIsTextLabel = new Label("Your score is: ");
        pane.add(yourScoreIsTextLabel, 1, 2);

        Label yourScoreIsAmountLabel = new Label();
        yourScoreIsAmountLabel.textProperty().bind(stageManager.getLevelProvider().getTotalScore().asString());
        pane.add(yourScoreIsAmountLabel, 2, 2);

        Button join = new Button("Join multi-player level!");
        join.setOnAction(e -> {
            awaitingMessage.textProperty().setValue("Awaiting second player...");

            stageManager.getMpGameController().connect();

            HashMap<String, Long> command = stageManager.getMpGameController().receiveCommand();
            if (command.get("type").equals(Network.CMD_START)) {
                stageManager.getLevelProvider().incrementLevel();
                stageManager.toScene(SceneType.GAME_SCENE);
            }
        });
        pane.add(join, 1, 3);

        Button goToMainMenu = new Button("Go to Main Menu");
        goToMainMenu.setOnAction(e -> {
            StageManager.getInstance().toScene(SceneType.MAIN_MENU_SCENE);
        });
        pane.add(goToMainMenu, 1, 4);

        stageManager.setTitle("Space Shooter: Lobby");

        return new Scene(pane, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT);
    }
}
