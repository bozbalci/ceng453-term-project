package com.twentythree.spaceclient.game.enemy;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import javafx.scene.paint.Color;

public class StandardEnemy extends AbstractEnemy {
    @Override
    long getMaxHealth() {
        return Game.ENEMY_MAX_HEALTH;
    }

    @Override
    Color getStrokeColor() {
        return Game.ENEMY_STROKE_COLOR;
    }

    @Override
    Color getFillColor() {
        return Game.ENEMY_FILL_COLOR;
    }

    @Override
    double getAttackInterval() {
        return Game.ENEMY_ATTACK_INTERVAL;
    }

    public StandardEnemy(GameManager manager, double x, double y) {
        initialize(manager, x, y);
    }
}
