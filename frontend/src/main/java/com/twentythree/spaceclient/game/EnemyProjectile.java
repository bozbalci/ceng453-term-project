package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class EnemyProjectile {
    private Circle self;
    private Timeline movement;
    private GameManager manager;

    EnemyProjectile(GameManager manager, double x, double y) {
        self = new Circle(x, y, Game.ENEMY_PROJECTILE_SIZE);
        self.setStroke(Game.ENEMY_PROJECTILE_STROKE_COLOR);
        self.setFill(Game.ENEMY_PROJECTILE_FILL_COLOR);

        this.manager = manager;
        this.manager.mount(self);

        launch();
    }

    private void launch() {
        movement = new Timeline(new KeyFrame(Duration.millis(Game.ENEMY_PROJECTILE_UPDATE_INTERVAL),
                e -> updatePosition()));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }

    public void stop() {
        movement.stop();
    }

    private void stopAndUnmount() {
        stop();
        manager.unmount(self);
    }

    private void updatePosition() {
        self.setCenterY(self.getCenterY() + Game.ENEMY_PROJECTILE_UPDATE_INCREMENT);

        if (self.getCenterY() >= GUI.WINDOW_HEIGHT) {
            stopAndUnmount();
        }

        Bounds projectileBounds = self.getBoundsInParent();

        Player player = manager.getPlayer();

        if (player.intersects(projectileBounds)) {
            stopAndUnmount();

            player.processHit();
        }
    }
}
