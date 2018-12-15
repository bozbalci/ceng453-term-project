package com.twentythree.spaceclient.entity;

import com.twentythree.spaceclient.constants.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player {
    private LongProperty healthProperty;
    private Rectangle self;
    private Timeline autoAttack;

    private GameManager manager;

    public Player(GameManager manager) {
        healthProperty = new SimpleLongProperty(Game.PLAYER_MAX_HEALTH);

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
        autoAttack = new Timeline(new KeyFrame(Duration.seconds(Game.PLAYER_ATTACK_INTERVAL), e -> {
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
    void processHit() {
        healthProperty.setValue(healthProperty.getValue() - 1);

        if (!isAlive()) {
            stopAttackAndUnmount();

            manager.gameOver();
        }
    }

    private boolean isAlive() {
        return healthProperty.getValue() > 0;
    }

    public LongProperty getHealthProperty() {
        return healthProperty;
    }
}
