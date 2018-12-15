package com.twentythree.spaceclient.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class EnemyProjectile {
    private Circle self;
    private Timeline movement;
    private GameManager manager;

    EnemyProjectile(GameManager manager, double x, double y) {
        self = new Circle(x, y, 2);
        self.setStroke(Color.BLACK);
        self.setFill(Color.RED);

        this.manager = manager;
        this.manager.mount(self);

        launch();
    }

    private void launch() {
        movement = new Timeline(new KeyFrame(Duration.millis(50), e -> updatePosition()));
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
        self.setCenterY(self.getCenterY() + 5);

        Bounds projectileBounds = self.getBoundsInParent();

        Player player = manager.getPlayer();

        if (player.intersects(projectileBounds)) {
            stopAndUnmount();

            player.processHit();
        }
    }
}
