package com.twentythree.spaceclient.game.projectile;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import javafx.scene.paint.Color;

public class RemotePlayerProjectile extends AbstractPlayerProjectile {
    public RemotePlayerProjectile(GameManager manager, double x, double y) {
        super(manager, x, y);
    }

    @Override
    protected Boolean isBlank() {
        return true;
    }

    @Override
    double getUpdateInterval() {
        return Game.REMOTE_PLAYER_PROJECTILE_UPDATE_INTERVAL;
    }

    @Override
    int getProjectileSize() {
        return Game.REMOTE_PLAYER_PROJECTILE_SIZE;
    }

    @Override
    Color getStrokeColor() {
        return Game.REMOTE_PLAYER_PROJECTILE_STROKE_COLOR;
    }

    @Override
    Color getFillColor() {
        return Game.REMOTE_PLAYER_PROJECTILE_FILL_COLOR;
    }


}
