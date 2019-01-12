package com.twentythree.spaceclient.game.projectile;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import javafx.scene.paint.Color;

public class LocalPlayerProjectile extends AbstractPlayerProjectile {
    public LocalPlayerProjectile(GameManager manager, double x, double y) {
        super(manager, x, y);
    }

    @Override
    protected Boolean isBlank() {
        return false;
    }

    @Override
    double getUpdateInterval() {
        return Game.LOCAL_PLAYER_PROJECTILE_UPDATE_INTERVAL;
    }

    @Override
    int getProjectileSize() {
        return Game.LOCAL_PLAYER_PROJECTILE_SIZE;
    }

    @Override
    Color getStrokeColor() {
        return Game.LOCAL_PLAYER_PROJECTILE_STROKE_COLOR;
    }

    @Override
    Color getFillColor() {
        return Game.LOCAL_PLAYER_PROJECTILE_FILL_COLOR;
    }

}
