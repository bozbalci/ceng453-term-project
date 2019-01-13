package com.twentythree.spaceclient.game.projectile;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import com.twentythree.spaceclient.game.player.AbstractPlayer;
import com.twentythree.spaceclient.game.player.LocalPlayer;
import com.twentythree.spaceclient.game.player.RemotePlayer;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

public class EnemyProjectile extends AbstractProjectile {
    public EnemyProjectile(GameManager manager, double x, double y) {
        super(manager, x, y);
    }

    @Override
    double getUpdateInterval() {
        return Game.ENEMY_PROJECTILE_UPDATE_INTERVAL;
    }

    @Override
    int getProjectileSize() {
        return Game.ENEMY_PROJECTILE_SIZE;
    }

    @Override
    Color getStrokeColor() {
        return Game.ENEMY_PROJECTILE_STROKE_COLOR;
    }

    @Override
    Color getFillColor() {
        return Game.ENEMY_PROJECTILE_FILL_COLOR;
    }

    @Override
    void updatePosition() {
        self.setCenterY(self.getCenterY() + Game.ENEMY_PROJECTILE_UPDATE_INCREMENT);

        if (self.getCenterY() >= GUI.WINDOW_HEIGHT) {
            stop();
        }

        Bounds projectileBounds = self.getBoundsInParent();
        LocalPlayer localPlayer = manager.getLocalPlayer();
        RemotePlayer remotePlayer = manager.getRemotePlayer();

        if (!localPlayer.isDead && localPlayer.intersects(projectileBounds)) {
            stop();

            localPlayer.processHit();
        }

        if (remotePlayer != null && !remotePlayer.isDead && remotePlayer.intersects(projectileBounds)) {
            stop();
        }
    }
}
