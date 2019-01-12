package com.twentythree.spaceclient.game.enemy;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import javafx.scene.paint.Color;

public class AttackResistantEnemy extends AbstractEnemy {
    @Override
    long getMaxHealth() {
        return Game.ATTACK_RESISTANT_ENEMY_MAX_HEALTH;
    }

    @Override
    Color getStrokeColor() {
        return Game.ATTACK_RESISTANT_ENEMY_STROKE_COLOR;
    }

    @Override
    Color getFillColor() {
        return Game.ATTACK_RESISTANT_ENEMY_FILL_COLOR;
    }

    @Override
    double getAttackInterval() {
        return Game.ATTACK_RESISTANT_ENEMY_ATTACK_INTERVAL;
    }

    public AttackResistantEnemy(GameManager manager, double x, double y) {
        initialize(manager, x, y);
    }
}
