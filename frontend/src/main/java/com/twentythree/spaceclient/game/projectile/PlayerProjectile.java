package com.twentythree.spaceclient.game.projectile;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import com.twentythree.spaceclient.game.enemy.AbstractEnemy;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.List;

public class PlayerProjectile {
    private Circle self;
    private Timeline movement;
    private GameManager manager;

    public PlayerProjectile(GameManager manager, double x, double y) {
        self = new Circle(x, y, Game.PLAYER_PROJECTILE_SIZE);
        self.setStroke(Game.PLAYER_PROJECTILE_STROKE_COLOR);
        self.setFill(Game.PLAYER_PROJECTILE_FILL_COLOR);

        this.manager = manager;
        this.manager.mount(self);

        launch();
    }

    private void launch() {
        movement = new Timeline(new KeyFrame(Duration.millis(Game.PLAYER_PROJECTILE_UPDATE_INTERVAL),
                e -> updatePosition()));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }

    private void stopAndUnmount() {
        movement.stop();
        manager.unmount(self);
    }

    private void updatePosition() {
        self.setCenterY(self.getCenterY() - Game.PLAYER_PROJECTILE_UPDATE_INCREMENT);

        if (self.getCenterY() <= 0) {
            stopAndUnmount();
        }

        Bounds projectileBounds = self.getBoundsInParent();

        List<AbstractEnemy> enemyList = manager.getSpawner().getEnemyList();
        Iterator<AbstractEnemy> it = enemyList.iterator();

        while (it.hasNext()) {
            AbstractEnemy enemy = it.next();

            if (enemy.intersects(projectileBounds)) {
                stopAndUnmount();

                boolean dead = enemy.handleHit();

                if (dead) {
                    it.remove();
                }
            }
        }
    }
}

