package com.twentythree.spaceclient.constants;

import javafx.scene.paint.Color;

public class Game {
    // TODO public static final double ENEMY_SPAWN_INTERVAL = 2.5;
    public static final double ENEMY_SPAWN_INTERVAL = 0.5;

    public static final int ENEMY_MAX_HEALTH = 2;
    public static final double ENEMY_ATTACK_INTERVAL = 1.55;

    public static final int ENEMY_SIZE = 25;
    public static final Color ENEMY_FILL_COLOR = Color.DARKGRAY;
    public static final Color ENEMY_STROKE_COLOR = Color.BLACK;

    public static final int ENEMY_PROJECTILE_SIZE = 2;
    public static final Color ENEMY_PROJECTILE_FILL_COLOR = Color.RED;
    public static final Color ENEMY_PROJECTILE_STROKE_COLOR = Color.BLACK;
    public static final double ENEMY_PROJECTILE_UPDATE_INTERVAL = 1;
    public static final double ENEMY_PROJECTILE_UPDATE_INCREMENT = 0.3;

    // TODO public static final int PLAYER_MAX_HEALTH = 3;
    public static final int PLAYER_MAX_HEALTH = 7;
    // public static final double PLAYER_ATTACK_INTERVAL = 0.55;
    public static final double PLAYER_ATTACK_INTERVAL = 0.25;

    public static final int PLAYER_SIZE = 25;
    public static final Color PLAYER_FILL_COLOR = Color.LIGHTBLUE;
    public static final Color PLAYER_STROKE_COLOR = Color.BLACK;

    public static final int PLAYER_PROJECTILE_SIZE = 2;
    public static final Color PLAYER_PROJECTILE_FILL_COLOR = Color.GREENYELLOW;
    public static final Color PLAYER_PROJECTILE_STROKE_COLOR = Color.BLACK;
    public static final double PLAYER_PROJECTILE_UPDATE_INTERVAL = 1;
    public static final double PLAYER_PROJECTILE_UPDATE_INCREMENT = 0.3;

    public static final long POINTS_PER_KILL = 50;
}
