package com.twentythree.spaceclient.game.projectile;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public abstract class AbstractProjectile {
    Circle self;
    private Timeline movement;
    protected GameManager manager;

    abstract double getUpdateInterval();
    abstract int getProjectileSize();
    abstract Color getStrokeColor();
    abstract Color getFillColor();
    abstract void updatePosition();

    AbstractProjectile(GameManager manager, double x, double y) {
        self = new Circle(x, y, getProjectileSize());
        self.setStroke(getStrokeColor());
        self.setFill(getFillColor());

        this.manager = manager;
        manager.mount(self);

        start();
    }

    void start() {
        movement = new Timeline(new KeyFrame(Duration.millis(getUpdateInterval()),
                e -> updatePosition()));
        movement.setCycleCount(Timeline.INDEFINITE);
        movement.play();
    }

    void stop() {
        movement.stop();
        manager.unmount(self);
    }
}
