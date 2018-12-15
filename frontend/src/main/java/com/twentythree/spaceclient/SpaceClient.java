package com.twentythree.spaceclient;

import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.scene.LoginScene;
import com.twentythree.spaceclient.stage.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class SpaceClient extends Application {
    @Override
    public void start(Stage primaryStage) {
        StageManager stageManager = StageManager.getInstance();
        stageManager.setStage(primaryStage);

        stageManager.toScene(SceneType.LOGIN_SCENE);

        stageManager.setNonresizable();
        stageManager.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
