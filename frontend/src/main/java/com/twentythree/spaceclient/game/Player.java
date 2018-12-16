package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.projectile.PlayerProjectile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Bounds;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player {
    private LongProperty healthProperty;
    private Rectangle self;
    private Timeline autoAttack;

    private GameManager manager;

    public Player(GameManager manager) {
        healthProperty = new SimpleLongProperty(Game.PLAYER_MAX_HEALTH);

        self = new Rectangle(GUI.WINDOW_WIDTH / 2.0, GUI.WINDOW_HEIGHT - GUI.LARGE_INSET,
                Game.PLAYER_SIZE , Game.PLAYER_SIZE);
        self.setStroke(Game.PLAYER_STROKE_COLOR);
        self.setFill(Game.PLAYER_FILL_COLOR);

        this.manager = manager;
        this.manager.mount(self);

        startAttack();
    }

    public void updatePosition(double x) {
        self.setX(x - self.getWidth() / 2);
    }

    public boolean intersects(Bounds bounds) {
        return self.getBoundsInParent().intersects(bounds);
    }

    private void startAttack() {
        autoAttack = new Timeline(new KeyFrame(Duration.seconds(Game.PLAYER_ATTACK_INTERVAL), e -> {
            PlayerProjectile projectile = new PlayerProjectile(manager,
                    self.getX() + self.getWidth() / 2, self.getY());
        }));
        autoAttack.setCycleCount(Timeline.INDEFINITE);
        autoAttack.play();
    }

    private void stopAndUnmount() {
        autoAttack.stop();
        manager.unmount(self);
    }

    // Returns true if the player was killed by invoking this method, false otherwise
    public void processHit() {
        healthProperty.setValue(healthProperty.getValue() - 1);

        if (!isAlive()) {
            stopAndUnmount();

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
