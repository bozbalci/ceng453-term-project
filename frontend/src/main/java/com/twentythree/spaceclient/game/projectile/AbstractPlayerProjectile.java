package com.twentythree.spaceclient.game.projectile;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import com.twentythree.spaceclient.game.enemy.AbstractEnemy;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.List;

public abstract class AbstractPlayerProjectile extends AbstractProjectile {
    public AbstractPlayerProjectile(GameManager manager, double x, double y) {
        super(manager, x, y);
    }

    protected abstract Boolean isBlank();

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

                boolean dead = enemy.handleHit(isBlank());

                if (dead) {
                    it.remove();
                }
            }
        }
    }
}

