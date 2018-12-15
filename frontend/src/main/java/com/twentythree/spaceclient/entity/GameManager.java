package com.twentythree.spaceclient.entity;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class GameManager {
    private Player player;
    private EnemySpawner spawner;
    private Pane _mountedPane;
    private long score;

    public GameManager(Pane pane) {
        _mountedPane = pane;

        player = new Player(this);
        spawner = new EnemySpawner(this);

        score = 0;

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
        return score;
    }

    public void setScore(long newScore) {
        score = newScore;
    }

    public void addScore(long points) {
        score += points;
    }

}
