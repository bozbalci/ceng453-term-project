package com.twentythree.spaceclient.game.enemy;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import javafx.scene.paint.Color;

public class RapidAttackEnemy extends AbstractEnemy {
    @Override
    long getMaxHealth() {
        return Game.RAPID_ATTACK_ENEMY_MAX_HEALTH;
    }

    @Override
    Color getStrokeColor() {
        return Game.RAPID_ATTACK_ENEMY_STROKE_COLOR;
    }

    @Override
    Color getFillColor() {
        return Game.RAPID_ATTACK_ENEMY_FILL_COLOR;
    }

    @Override
    double getAttackInterval() {
        return Game.RAPID_ATTACK_ENEMY_ATTACK_INTERVAL;
    }

    public RapidAttackEnemy(GameManager manager, double x, double y) {
        initialize(manager, x, y);
    }
}
