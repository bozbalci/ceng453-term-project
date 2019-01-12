package com.twentythree.spaceclient.game.player;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import com.twentythree.spaceclient.game.projectile.RemotePlayerProjectile;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class RemotePlayer extends AbstractPlayer {
    public RemotePlayer(GameManager manager) {
        super(manager);
    }

    @Override
    protected Color getStrokeColor() {
        return Game.REMOTE_PLAYER_STROKE_COLOR;
    }

    @Override
    protected Color getFillColor() {
        return Game.REMOTE_PLAYER_FILL_COLOR;
    }

    @Override
    protected void startAttack() {
        autoAttack = new Timeline(new KeyFrame(Duration.seconds(Game.PLAYER_ATTACK_INTERVAL), e -> {
            RemotePlayerProjectile projectile = new RemotePlayerProjectile(manager,
                    self.getX() + self.getWidth() / 2, self.getY());
        }));
        autoAttack.setCycleCount(Timeline.INDEFINITE);
        autoAttack.play();
    }
}
