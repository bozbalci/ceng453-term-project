package com.twentythree.spaceclient.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Enemy {
    private long health;
    private Rectangle self;
    private GameManager manager;
    private Timeline autoAttack;

    public Enemy(GameManager manager) {
        self = new Rectangle(new Random().nextInt(200), 20, 10, 10);
        self.setStroke(Color.BLACK);
        self.setFill(Color.BLACK);

        this.manager = manager;
        this.manager.mount(self);

        health = 2;

        startAttack();
    }

    private void startAttack() {
        autoAttack = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> {
            EnemyProjectile projectile = new EnemyProjectile(manager,
                    self.getX() + self.getWidth() / 2, self.getY() + self.getHeight());
        }));
        autoAttack.setCycleCount(Timeline.INDEFINITE);
        autoAttack.play();
    }

    private void stopAttackAndUnmount() {
        stopAttack();
        this.manager.unmount(self);
    }

    private void stopAttack() {
        autoAttack.stop();
    }

    boolean intersects(Bounds bounds) {
        return self.getBoundsInParent().intersects(bounds);
    }

    private boolean isAlive() {
        return health > 0;
    }

    // Returns true if the enemy is killed from this method, false otherwise
    boolean processHit() {
        health--;

        if (!isAlive()) {
            manager.addScore(50);

            stopAttackAndUnmount();

            return true;
        } else {
            return false;
        }
    }
}
