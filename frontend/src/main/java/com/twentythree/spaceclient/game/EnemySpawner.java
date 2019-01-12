package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.controller.StageManager;
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
    private int aliveCount;

    EnemySpawner(GameManager manager) {
        this.manager = manager;
        enemyList = new ArrayList<>();
        createEnemies();
        this.aliveCount = enemyList.size();
    }

    public void processKill() {
        aliveCount--;

        if (aliveCount <= 0) {
            manager.victory();
        }
    }

    private void createEnemies() {
        int currentLevel = StageManager.getInstance().getLevelProvider().getCurrentLevel().intValue();
        enemyList = new ArrayList<>();

        switch (currentLevel) {
            case 1:
                enemyList.add(new StandardEnemy(manager, 100, 100));
                enemyList.add(new StandardEnemy(manager, 150, 120));
                enemyList.add(new StandardEnemy(manager, 200, 130));
                enemyList.add(new StandardEnemy(manager, 250, 120));
                enemyList.add(new StandardEnemy(manager, 300, 100));
                break;
            case 2:
                enemyList.add(new RapidAttackEnemy(manager, 150, 100));
                enemyList.add(new RapidAttackEnemy(manager, 250, 100));
                enemyList.add(new StandardEnemy(manager, 100, 200));
                enemyList.add(new StandardEnemy(manager, 150, 220));
                enemyList.add(new StandardEnemy(manager, 200, 230));
                enemyList.add(new StandardEnemy(manager, 250, 220));
                enemyList.add(new StandardEnemy(manager, 300, 200));
                break;
            case 3:
                // enemyList.add(new RapidAttackEnemy(manager, 150, 100));
                // enemyList.add(new RapidAttackEnemy(manager, 250, 100));
                // enemyList.add(new StandardEnemy(manager, 100, 200));
                // enemyList.add(new AttackResistantEnemy(manager, 150, 220));
                // enemyList.add(new StandardEnemy(manager, 200, 230));
                // enemyList.add(new AttackResistantEnemy(manager, 250, 220));
                enemyList.add(new StandardEnemy(manager, 300, 200));
                break;
            case 4:
                enemyList.add(new RapidAttackEnemy(manager, 150, 100));
                enemyList.add(new RapidAttackEnemy(manager, 250, 100));
                enemyList.add(new StandardEnemy(manager, 100, 200));
                enemyList.add(new AttackResistantEnemy(manager, 150, 220));
                enemyList.add(new StandardEnemy(manager, 200, 230));
                enemyList.add(new AttackResistantEnemy(manager, 250, 220));
                enemyList.add(new StandardEnemy(manager, 300, 200));
                break;
            default:
                break;
        }
    }

    public List<AbstractEnemy> getEnemyList() {
        return enemyList;
    }
}
