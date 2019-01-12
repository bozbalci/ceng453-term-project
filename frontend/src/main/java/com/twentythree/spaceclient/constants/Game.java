package com.twentythree.spaceclient.constants;

import javafx.scene.paint.Color;

public class Game {
    public static final int ENEMY_MAX_HEALTH = 2;
    public static final double ENEMY_ATTACK_INTERVAL = 1.75;

    public static final int ENEMY_SIZE = 30;
    public static final Color ENEMY_FILL_COLOR = Color.DARKKHAKI;
    public static final Color ENEMY_STROKE_COLOR = Color.BLACK;

    public static final int ENEMY_PROJECTILE_SIZE = 3;
    public static final Color ENEMY_PROJECTILE_FILL_COLOR = Color.RED;
    public static final Color ENEMY_PROJECTILE_STROKE_COLOR = Color.BLACK;
    public static final double ENEMY_PROJECTILE_UPDATE_INTERVAL = 1;
    public static final double ENEMY_PROJECTILE_UPDATE_INCREMENT = 0.25;

    public static final int PLAYER_MAX_HEALTH = 3;
    public static final double PLAYER_ATTACK_INTERVAL = 0.30;

    public static final int PLAYER_SIZE = 25;

    public static final Color LOCAL_PLAYER_FILL_COLOR = Color.LIGHTBLUE;
    public static final Color LOCAL_PLAYER_STROKE_COLOR = Color.BLACK;
    public static final Color REMOTE_PLAYER_FILL_COLOR = Color.GREENYELLOW;
    public static final Color REMOTE_PLAYER_STROKE_COLOR = Color.BLACK;

    public static final int LOCAL_PLAYER_PROJECTILE_SIZE = 3;
    public static final Color LOCAL_PLAYER_PROJECTILE_FILL_COLOR = Color.LIGHTBLUE;
    public static final Color LOCAL_PLAYER_PROJECTILE_STROKE_COLOR = Color.BLACK;
    public static final double LOCAL_PLAYER_PROJECTILE_UPDATE_INTERVAL = 1;

    public static final int REMOTE_PLAYER_PROJECTILE_SIZE = 3;
    public static final Color REMOTE_PLAYER_PROJECTILE_FILL_COLOR = Color.GREENYELLOW;
    public static final Color REMOTE_PLAYER_PROJECTILE_STROKE_COLOR = Color.BLACK;
    public static final double REMOTE_PLAYER_PROJECTILE_UPDATE_INTERVAL = 1;

    public static final double PLAYER_PROJECTILE_UPDATE_INCREMENT = 0.35;

    public static final long POINTS_PER_KILL = 50;

    // Custom enemy types
    public static final int ATTACK_RESISTANT_ENEMY_MAX_HEALTH = 5;
    public static final double ATTACK_RESISTANT_ENEMY_ATTACK_INTERVAL = 2.25;
    public static final Color ATTACK_RESISTANT_ENEMY_FILL_COLOR = Color.CORAL;
    public static final Color ATTACK_RESISTANT_ENEMY_STROKE_COLOR = Color.BLACK;

    public static final int RAPID_ATTACK_ENEMY_MAX_HEALTH = 1;
    public static final double RAPID_ATTACK_ENEMY_ATTACK_INTERVAL = 0.65;
    public static final Color RAPID_ATTACK_ENEMY_FILL_COLOR = Color.CRIMSON;
    public static final Color RAPID_ATTACK_ENEMY_STROKE_COLOR = Color.BLACK;

    public static final Integer MULTIPLAYER_LEVEL_INDEX = 4;
}
