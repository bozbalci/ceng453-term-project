package com.twentythree.spaceclient.game.enemy;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;

public class BaseEnemy extends AbstractEnemy {
    public BaseEnemy(GameManager manager) {
        maxHealth = Game.ENEMY_MAX_HEALTH;
        strokeColor = Game.ENEMY_STROKE_COLOR;
        fillColor = Game.ENEMY_FILL_COLOR;
        attackInterval = Game.ENEMY_ATTACK_INTERVAL;

        this.initialize(manager);
    }
}
