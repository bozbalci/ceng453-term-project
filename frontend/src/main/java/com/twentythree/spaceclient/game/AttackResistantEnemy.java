package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.Game;

class AttackResistantEnemy extends AbstractEnemy {
    AttackResistantEnemy(GameManager manager) {
        maxHealth = Game.ATTACK_RESISTANT_ENEMY_MAX_HEALTH;
        strokeColor = Game.ATTACK_RESISTANT_ENEMY_STROKE_COLOR;
        fillColor = Game.ATTACK_RESISTANT_ENEMY_FILL_COLOR;
        attackInterval = Game.ATTACK_RESISTANT_ENEMY_ATTACK_INTERVAL;

        initialize(manager);
    }
}
