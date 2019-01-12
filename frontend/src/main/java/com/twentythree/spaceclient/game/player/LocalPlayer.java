package com.twentythree.spaceclient.game.player;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import com.twentythree.spaceclient.game.projectile.LocalPlayerProjectile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class LocalPlayer extends AbstractPlayer {
    private LongProperty healthProperty;

    public LocalPlayer(GameManager manager) {
        super(manager);

        healthProperty = new SimpleLongProperty(Game.PLAYER_MAX_HEALTH);
    }

    public void processHit() {
        healthProperty.setValue(healthProperty.getValue() - 1);

        if (!isAlive()) {
            stopAndUnmount();

            manager.onLocalPlayerDead();
        }
    }

    public Boolean isAlive() {
        return healthProperty.getValue() > 0;
    }

    public LongProperty getHealthProperty() {
        return healthProperty;
    }

    @Override
    protected void startAttack() {
        autoAttack = new Timeline(new KeyFrame(Duration.seconds(Game.PLAYER_ATTACK_INTERVAL), e -> {
            LocalPlayerProjectile projectile = new LocalPlayerProjectile(manager,
                    self.getX() + self.getWidth() / 2, self.getY());
        }));
        autoAttack.setCycleCount(Timeline.INDEFINITE);
        autoAttack.play();
    }

    @Override
    protected Color getStrokeColor() {
        return Game.LOCAL_PLAYER_STROKE_COLOR;
    }

    @Override
    protected Color getFillColor() {
        return Game.LOCAL_PLAYER_FILL_COLOR;
    }
}
