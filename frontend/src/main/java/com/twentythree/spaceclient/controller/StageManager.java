package com.twentythree.spaceclient.controller;

import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.scene.*;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageManager {
    private static StageManager instance = new StageManager();

    private StageManager() {
        levelProvider = new LevelProvider();
        requestController = new HttpRequestController();
        mpGameController = new MPGameController();
    }

    public static StageManager getInstance() {
        return instance;
    }

    private Stage stage;
    private LevelProvider levelProvider;
    private HttpRequestController requestController;
    private MPGameController mpGameController;

    public void setStage(Stage newStage) {
        stage = newStage;
    }

    private void setScene(Scene scene) {
        stage.setScene(scene);
    }

    public void setTitle(String title) {
        stage.setTitle(title);
    }

    public void setNonresizable() {
        stage.setResizable(false);
    }

    public void show() {
        stage.show();
    }

    public void toScene(SceneType sceneType) {
        switch (sceneType) {
            case LOGIN_SCENE:
                setScene(new LoginScene().getScene(this));
                break;
            case MAIN_MENU_SCENE:
                setScene(new MainMenuScene().getScene(this));
                break;
            case GAME_SCENE:
                setScene(new GameScene().getScene(this));
                break;
            case GAME_OVER_SCENE:
                setScene(new GameOverScene().getScene(this));
                break;
            case VICTORY_SCENE:
                setScene(new VictoryScene().getScene(this));
                break;
            case MATCHMAKING_SCENE:
                setScene(new MatchmakingScene().getScene(this));
                break;
            case LEADERBOARD_SCENE:
                setScene(new LeaderboardScene().getScene(this));
                break;
            case ENDGAME_SCENE:
                setScene(new EndgameScene().getScene(this));
                break;
        }
    }

    public LevelProvider getLevelProvider() {
        return levelProvider;
    }

    public HttpRequestController getRequestController() {
        return requestController;
    }

    public MPGameController getMpGameController() {
        return mpGameController;
    }

    public void submitScore() {
        requestController.submitScore(levelProvider.getTotalScore().getValue());
    }
}

