package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.enemy.AbstractEnemy;
import com.twentythree.spaceclient.game.enemy.AttackResistantEnemy;
import com.twentythree.spaceclient.game.enemy.StandardEnemy;
import com.twentythree.spaceclient.game.enemy.RapidAttackEnemy;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemySpawner {
    private GameManager manager;
    private List<AbstractEnemy> enemyList;
    private Timeline spawn;
    private Long enemyCount;
    private Long aliveCount;

    EnemySpawner(GameManager manager, Long enemyCount) {
        this.manager = manager;
        this.enemyCount = enemyCount;
        this.aliveCount = enemyCount;

        enemyList = new ArrayList<>();

        start();
    }

    public void handleKill() {
        aliveCount--;

        if (aliveCount <= 0) {
            manager.victory();
        }
    }

    private void start() {
        spawn = new Timeline(new KeyFrame(Duration.seconds(Game.ENEMY_SPAWN_INTERVAL), e -> {
            double random = new Random().nextDouble();
            AbstractEnemy enemy;

            if (random <= 0.25) {
                enemy = new RapidAttackEnemy(manager);
            } else if (random <= 0.50) {
                enemy = new AttackResistantEnemy(manager);
            } else {
                enemy = new StandardEnemy(manager);
            }

            enemyList.add(enemy);
        }));

        spawn.setCycleCount(enemyCount.intValue());
        spawn.play();
    }

    void stop() {
        spawn.stop();
    }

    public List<AbstractEnemy> getEnemyList() {
        return enemyList;
    }
}
