package com.twentythree.spaceclient.entity;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.Game;
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
        self = new Rectangle(new Random().nextInt(GUI.WINDOW_WIDTH - GUI.DEFAULT_INSET - Game.ENEMY_SIZE),
                GUI.LARGE_INSET, Game.ENEMY_SIZE, Game.ENEMY_SIZE);
        self.setStroke(Color.BLACK);
        self.setFill(Color.BLACK);

        this.manager = manager;
        this.manager.mount(self);

        health = Game.ENEMY_MAX_HEALTH;

        startAttack();
    }

    private void startAttack() {
        autoAttack = new Timeline(new KeyFrame(Duration.seconds(Game.ENEMY_ATTACK_INTERVAL), e -> {
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
            manager.addScore(Game.POINTS_PER_KILL);

            stopAttackAndUnmount();

            manager.getSpawner().processKill();

            return true;
        } else {
            return false;
        }
    }
}
