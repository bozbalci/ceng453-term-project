package com.twentythree.spaceclient.game.enemy;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.projectile.EnemyProjectile;
import com.twentythree.spaceclient.game.GameManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public abstract class AbstractEnemy {
    private long health;
    private Rectangle self;
    private GameManager manager;
    private Timeline autoAttack;

    abstract long getMaxHealth();
    abstract Color getStrokeColor();
    abstract Color getFillColor();
    abstract double getAttackInterval();

    void initialize(GameManager manager) {
        self = new Rectangle(new Random().nextInt(GUI.WINDOW_WIDTH - GUI.DEFAULT_INSET - Game.ENEMY_SIZE),
                GUI.DEFAULT_INSET + new Random().nextInt((int) (GUI.LARGE_INSET * GUI.INSET_DEVIATION)),
                Game.ENEMY_SIZE, Game.ENEMY_SIZE);
        self.setStroke(getStrokeColor());
        self.setFill(getFillColor());

        this.manager = manager;
        this.manager.mount(self);

        health = getMaxHealth();

        start();
    }

    private void start() {
        autoAttack = new Timeline(new KeyFrame(Duration.seconds(getAttackInterval()), e -> {
            EnemyProjectile projectile = new EnemyProjectile(manager,
                    self.getX() + self.getWidth() / 2, self.getY() + self.getHeight());
        }));
        autoAttack.setCycleCount(Timeline.INDEFINITE);
        autoAttack.play();
    }

    private void stop() {
        autoAttack.stop();
        this.manager.unmount(self);
    }

    public boolean intersects(Bounds bounds) {
        return self.getBoundsInParent().intersects(bounds);
    }

    private boolean isAlive() {
        return health > 0;
    }

    // Returns true if the enemy is killed from this method, false otherwise
    public boolean handleHit() {
        health--;

        if (!isAlive()) {
            manager.addScore(Game.POINTS_PER_KILL);

            stop();

            manager.getSpawner().handleKill();

            return true;
        } else {
            return false;
        }
    }
}
