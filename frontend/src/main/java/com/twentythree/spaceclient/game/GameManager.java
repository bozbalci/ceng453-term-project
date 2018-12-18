package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.controller.LevelProvider;
import com.twentythree.spaceclient.controller.StageManager;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GameManager {
    private Player player;
    private EnemySpawner spawner;
    private Pane mountedPane;
    private LongProperty scoreProperty;
    private boolean isFinished;

    public GameManager(Pane pane, LevelProvider levelProvider) {
        mountedPane = pane;

        player = new Player(this);
        spawner = new EnemySpawner(this, levelProvider.getEnemyCountForLevel());

        scoreProperty = new SimpleLongProperty(levelProvider.getTotalScore().getValue());
        isFinished = false;
    }

    public void mount(Node node) {
        mountedPane.getChildren().add(node);
    }

    public void unmount(Node node) {
        mountedPane.getChildren().remove(node);
    }

    public Player getPlayer() {
        return player;
    }

    public EnemySpawner getSpawner() {
        return spawner;
    }

    public LongProperty getScoreProperty() {
        return scoreProperty;
    }

    public void addScore(long points) {
        scoreProperty.setValue(scoreProperty.getValue() + points);
    }

    private void endAndRedirect(SceneType targetScene) {
        if (!isFinished) {
            isFinished = true;
            spawner.stop();

            StageManager stageManager =  StageManager.getInstance();
            stageManager.getLevelProvider().setScore(scoreProperty.getValue());
            stageManager.toScene(targetScene);
        }
    }

    void gameOver() {
        endAndRedirect(SceneType.GAME_OVER_SCENE);
    }

    void victory() {
        endAndRedirect(SceneType.VICTORY_SCENE);
    }
}
