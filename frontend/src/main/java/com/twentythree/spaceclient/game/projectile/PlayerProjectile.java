package com.twentythree.spaceclient.game.projectile;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import com.twentythree.spaceclient.game.enemy.AbstractEnemy;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.List;

public class PlayerProjectile extends AbstractProjectile {
    public PlayerProjectile(GameManager manager, double x, double y) {
        super(manager, x, y);
    }

    @Override
    double getUpdateInterval() {
        return Game.PLAYER_PROJECTILE_UPDATE_INTERVAL;
    }

    @Override
    int getProjectileSize() {
        return Game.PLAYER_PROJECTILE_SIZE;
    }

    @Override
    Color getStrokeColor() {
        return Game.PLAYER_PROJECTILE_STROKE_COLOR;
    }

    @Override
    Color getFillColor() {
        return Game.PLAYER_PROJECTILE_FILL_COLOR;
    }

    void updatePosition() {
        self.setCenterY(self.getCenterY() - Game.PLAYER_PROJECTILE_UPDATE_INCREMENT);

        if (self.getCenterY() <= 0) {
            stop();
        }

        Bounds projectileBounds = self.getBoundsInParent();

        List<AbstractEnemy> enemyList = manager.getSpawner().getEnemyList();
        Iterator<AbstractEnemy> it = enemyList.iterator();

        while (it.hasNext()) {
            AbstractEnemy enemy = it.next();

            if (enemy.intersects(projectileBounds)) {
                stop();

                boolean dead = enemy.handleHit();

                if (dead) {
                    it.remove();
                }
            }
        }
    }
}

