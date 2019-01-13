package com.twentythree.spaceclient.game.player;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.game.GameManager;
import javafx.animation.Timeline;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class AbstractPlayer {
    protected Rectangle self;
    protected Timeline autoAttack;
    protected GameManager manager;
    public boolean isDead = false;

    public AbstractPlayer(GameManager manager) {
        self = new Rectangle(GUI.WINDOW_WIDTH / 2.0, GUI.WINDOW_HEIGHT - GUI.LARGE_INSET,
                Game.PLAYER_SIZE , Game.PLAYER_SIZE);
        self.setStroke(getStrokeColor());
        self.setFill(getFillColor());

        this.manager = manager;
        this.manager.mount(self);

        startAttack();
    }

    protected abstract Color getStrokeColor();
    protected abstract Color getFillColor();

    public double getPosition() {
        return self.getX();
    }

    public void updatePosition(double x) {
        self.setX(x - self.getWidth() / 2);
    }

    public boolean intersects(Bounds bounds) {
        return self.getBoundsInParent().intersects(bounds);
    }

    protected abstract void startAttack();

    public void stopAndUnmount() {
        isDead = true;
        autoAttack.stop();
        manager.unmount(self);
    }
}
