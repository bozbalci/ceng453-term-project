package com.twentythree.spaceclient.entity;

import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.stage.StageManager;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GameManager {
    private Player player;
    private EnemySpawner spawner;
    private Pane _mountedPane;
    private LongProperty scoreProperty;
    private boolean isFinished;

    public GameManager(Pane pane) {
        _mountedPane = pane;

        player = new Player(this);
        spawner = new EnemySpawner(this, 10L);

        scoreProperty = new SimpleLongProperty(0);
        isFinished = false;
    }

    void mount(Node node) {
        _mountedPane.getChildren().add(node);
    }

    void unmount(Node node) {
        _mountedPane.getChildren().remove(node);
    }

    public Player getPlayer() {
        return player;
    }

    public EnemySpawner getSpawner() {
        return spawner;
    }

    public long getScore() {
        return scoreProperty.getValue();
    }

    public LongProperty getScoreProperty() {
        return scoreProperty;
    }

    public void addScore(long points) {
        scoreProperty.setValue(scoreProperty.getValue() + points);
    }

    public void gameOver() {
        if (!isFinished) {
            isFinished = true;
            spawner.stopSpawn();

            StageManager stageManager =  StageManager.getInstance();
            stageManager.setLastGameScore(getScore());
            stageManager.toScene(SceneType.GAME_OVER_SCENE);
        }
    }

    public void victory() {
        if (!isFinished) {
            isFinished = true;
            spawner.stopSpawn();

            StageManager stageManager = StageManager.getInstance();
            stageManager.setLastGameScore(getScore());
            stageManager.toScene(SceneType.VICTORY_SCENE);
        }
    }
}
