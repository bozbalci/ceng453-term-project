package com.twentythree.spaceclient.game.enemy;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;

public class RapidAttackEnemy extends AbstractEnemy {
    public RapidAttackEnemy(GameManager manager) {
        maxHealth = Game.RAPID_ATTACK_ENEMY_MAX_HEALTH;
        strokeColor = Game.RAPID_ATTACK_ENEMY_STROKE_COLOR;
        fillColor = Game.RAPID_ATTACK_ENEMY_FILL_COLOR;
        attackInterval = Game.RAPID_ATTACK_ENEMY_ATTACK_INTERVAL;

        initialize(manager);
    }
}
