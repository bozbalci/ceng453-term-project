package com.twentythree.spaceclient.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class EnemySpawner {
    private GameManager manager;

    private List<Enemy> enemyList;

    private Timeline spawn;

    public EnemySpawner(GameManager manager) {
        this.manager = manager;
        enemyList = new ArrayList<>();

        startSpawn();
    }

    private void startSpawn() {
        spawn = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
            Enemy enemy = new Enemy(manager);

            enemyList.add(enemy);
        }));

        spawn.setCycleCount(10);
        spawn.play();
    }

    private void stopSpawn() {
        spawn.stop();
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }
}
