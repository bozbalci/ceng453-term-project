package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.Game;

class RapidAttackEnemy extends AbstractEnemy {
    RapidAttackEnemy(GameManager manager) {
        maxHealth = Game.RAPID_ATTACK_ENEMY_MAX_HEALTH;
        strokeColor = Game.RAPID_ATTACK_ENEMY_STROKE_COLOR;
        fillColor = Game.RAPID_ATTACK_ENEMY_FILL_COLOR;
        attackInterval = Game.RAPID_ATTACK_ENEMY_ATTACK_INTERVAL;

        initialize(manager);
    }
}
