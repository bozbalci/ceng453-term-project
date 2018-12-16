package com.twentythree.spaceclient.game.enemy;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;

public class AttackResistantEnemy extends AbstractEnemy {
    public AttackResistantEnemy(GameManager manager) {
        maxHealth = Game.ATTACK_RESISTANT_ENEMY_MAX_HEALTH;
        strokeColor = Game.ATTACK_RESISTANT_ENEMY_STROKE_COLOR;
        fillColor = Game.ATTACK_RESISTANT_ENEMY_FILL_COLOR;
        attackInterval = Game.ATTACK_RESISTANT_ENEMY_ATTACK_INTERVAL;

        initialize(manager);
    }
}
