package com.twentythree.spaceclient.scene;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.stage.StageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class VictoryScene {
    private GridPane pane;
    private StageManager stageManager;
    private Scene scene;

    private VictoryScene(GridPane pane, Scene scene) {
        this.pane = pane;
        this.scene = scene;
    }

    private static VictoryScene instance;

    public static VictoryScene create(StageManager stageManager) {
        GridPane pane = new GridPane();

        pane.setAlignment(Pos.CENTER);
        pane.setHgap(GUI.DEFAULT_GAP);
        pane.setVgap(GUI.DEFAULT_GAP);
        pane.setPadding(new Insets(GUI.DEFAULT_INSET, GUI.DEFAULT_INSET, GUI.DEFAULT_INSET, GUI.DEFAULT_INSET));

        Label gameOverLabel = new Label("Victory!");
        pane.add(gameOverLabel, 1, 1);

        Label yourScoreIsTextLabel = new Label("Your score is: ");
        pane.add(yourScoreIsTextLabel, 1, 2);

        Label yourScoreIsAmountLabel = new Label(stageManager.getLastGameScore().toString());
        pane.add(yourScoreIsAmountLabel, 2, 2);

        Button goToMainMenu = new Button("Go to Main Menu");
        goToMainMenu.setOnAction(e -> {
            StageManager.getInstance().toScene(SceneType.MAIN_MENU_SCENE);
        });
        pane.add(goToMainMenu, 1, 3);

        instance = new VictoryScene(pane, new Scene(pane, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT));

        instance.stageManager = stageManager;

        stageManager.setTitle("Space Shooter: Victory!");

        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}