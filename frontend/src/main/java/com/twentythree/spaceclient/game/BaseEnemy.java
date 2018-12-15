package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.Game;
import javafx.scene.paint.Color;

class BaseEnemy extends AbstractEnemy {
    BaseEnemy(GameManager manager) {
        maxHealth = Game.ENEMY_MAX_HEALTH;
        strokeColor = Game.ENEMY_STROKE_COLOR;
        fillColor = Game.ENEMY_FILL_COLOR;
        attackInterval = Game.ENEMY_ATTACK_INTERVAL;

        this.initialize(manager);
    }
}
