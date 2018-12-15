package com.twentythree.spaceclient;

import com.twentythree.spaceclient.entity.GameManager;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene {
    public static Scene get() {
        Pane pane = new Pane();
        GameManager manager = new GameManager(pane);
        Scene scene = new Scene(pane, 200, 200);

        scene.setOnMouseMoved(e -> manager.getPlayer().updatePosition(e.getX(), e.getY()));

        return scene;
    }
}
