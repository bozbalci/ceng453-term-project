package com.twentythree.spaceclient.entity;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player {
    private long health;
    private Rectangle self;
    private Timeline autoAttack;

    private GameManager manager;

    public Player(GameManager manager) {
        health = 3;

        self = new Rectangle(20, 20, 15 , 15);
        self.setStroke(Color.BLACK);
        self.setFill(Color.WHITE);

        this.manager = manager;
        this.manager.mount(self);

        startAttack();
    }

    private double getX() {
        return self.getX();
    }

    private double getY() {
        return self.getY();
    }

    public void updatePosition(double x, double y) {
        self.setX(x - self.getWidth() / 2);
        self.setY(y - self.getHeight() / 2);
    }

    boolean intersects(Bounds bounds) {
        return self.getBoundsInParent().intersects(bounds);
    }

    private void startAttack() {
        autoAttack = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            PlayerProjectile projectile = new PlayerProjectile(manager, getX() + self.getWidth() / 2, getY());
        }));
        autoAttack.setCycleCount(Timeline.INDEFINITE);
        autoAttack.play();
    }

    private void stopAttackAndUnmount() {
        stopAttack();
        manager.unmount(self);
    }

    private void stopAttack() {
        autoAttack.stop();
    }

    // Returns true if the player was killed by invoking this method, false otherwise
    boolean processHit() {
        health--;

        if (!isAlive()) {
            stopAttackAndUnmount();
            System.out.println("Game Over!");

            return true;
        } else {
            return false;
        }
    }

    private boolean isAlive() {
        return health > 0;
    }
}
