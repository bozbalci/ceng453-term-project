package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class EnemySpawner {
    private GameManager manager;
    private List<Enemy> enemyList;
    private Timeline spawn;
    private Long enemyCount;
    private Long aliveCount;

    public EnemySpawner(GameManager manager, Long enemyCount) {
        this.manager = manager;
        this.enemyCount = enemyCount;
        this.aliveCount = enemyCount;

        enemyList = new ArrayList<>();

        startSpawn();
    }

    public void processKill() {
        aliveCount--;

        if (aliveCount <= 0) {
            manager.victory();
        }
    }

    private void startSpawn() {
        spawn = new Timeline(new KeyFrame(Duration.seconds(Game.ENEMY_SPAWN_INTERVAL), e -> {
            Enemy enemy = new Enemy(manager);

            enemyList.add(enemy);
        }));

        spawn.setCycleCount(enemyCount.intValue());
        spawn.play();
    }

    void stopSpawn() {
        spawn.stop();
    }

    List<Enemy> getEnemyList() {
        return enemyList;
    }
}
