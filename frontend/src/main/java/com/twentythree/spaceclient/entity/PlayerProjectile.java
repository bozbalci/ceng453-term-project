package com.twentythree.spaceclient.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.Iterator;
import java.util.List;

public class PlayerProjectile {
    private Circle self;
    private Timeline movement;
    private GameManager manager;

    PlayerProjectile(GameManager manager, double x, double y) {
        self = new Circle(x, y, 2);
        self.setStroke(Color.BLACK);
        self.setFill(Color.BLUE);

        this.manager = manager;
        this.manager.mount(self);

        launch();
    }

    private void launch() {
        movement = new Timeline(new KeyFrame(Duration.millis(50), e -> updatePosition()));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }

    private void stopAndUnmount() {
        stop();
        manager.unmount(self);
    }

    private void stop() {
        movement.stop();
    }

    private void updatePosition() {
        self.setCenterY(self.getCenterY() - 5);

        Bounds projectileBounds = self.getBoundsInParent();

        List<Enemy> enemyList = manager.getSpawner().getEnemyList();
        Iterator<Enemy> it = enemyList.iterator();

        while (it.hasNext()) {
            Enemy enemy = it.next();

            if (enemy.intersects(projectileBounds)) {
                stopAndUnmount();

                boolean dead = enemy.processHit();

                if (dead) {
                    it.remove();
                    System.out.println("Points: " + manager.getScore());
                }
            }
        }
    }
}

