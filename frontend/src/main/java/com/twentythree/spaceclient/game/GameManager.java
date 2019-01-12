package com.twentythree.spaceclient.game;

import com.twentythree.spaceclient.constants.Game;
import com.twentythree.spaceclient.constants.Network;
import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.controller.LevelProvider;
import com.twentythree.spaceclient.controller.StageManager;
import com.twentythree.spaceclient.game.player.LocalPlayer;
import com.twentythree.spaceclient.game.player.RemotePlayer;
import javafx.application.Platform;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class GameManager {
    private LocalPlayer localPlayer;
    private RemotePlayer remotePlayer;
    private EnemySpawner spawner;
    private Pane mountedPane;
    private LongProperty scoreProperty;
    private boolean isFinished;
    private LevelProvider levelProvider;
    private Thread sendThread, recvThread;

    public GameManager(Pane pane, LevelProvider levelProvider) {
        mountedPane = pane;
        this.levelProvider = levelProvider;

        localPlayer = new LocalPlayer(this);
        spawner = new EnemySpawner(this);

        scoreProperty = new SimpleLongProperty(levelProvider.getTotalScore().getValue());
        isFinished = false;

        if (isMPLevel()) {
            initializeMPLevel();
        }
    }

    public void mount(Node node) {
        mountedPane.getChildren().add(node);
    }

    public void unmount(Node node) {
        mountedPane.getChildren().remove(node);
    }

    public LocalPlayer getLocalPlayer() {
        return localPlayer;
    }

    public EnemySpawner getSpawner() {
        return spawner;
    }

    public LongProperty getScoreProperty() {
        return scoreProperty;
    }

    public void addScore(long points) {
        scoreProperty.setValue(scoreProperty.getValue() + points);
    }

    private Boolean isMPLevel() {
        return levelProvider.getCurrentLevel().getValue().equals(Game.MULTIPLAYER_LEVEL_INDEX);
    }

    private void endAndRedirect(SceneType targetScene) {
        if (!isFinished) {
            isFinished = true;
            mountedPane.getChildren().clear();

            if (isMPLevel()) {
                stopSync();
            }

            StageManager stageManager = StageManager.getInstance();
            stageManager.getLevelProvider().setScore(scoreProperty.getValue());
            stageManager.toScene(targetScene);
        }
    }

    public void gameOver() {
        endAndRedirect(SceneType.GAME_OVER_SCENE);
    }

    public void victory() {
        long currentLevel = levelProvider.getCurrentLevel().getValue().longValue();

        if (currentLevel == Game.MULTIPLAYER_LEVEL_INDEX - 1) {
            endAndRedirect(SceneType.MATCHMAKING_SCENE);
        } else  if (currentLevel == Game.MULTIPLAYER_LEVEL_INDEX) {
            endAndRedirect(SceneType.ENDGAME_SCENE);
        } else {
            endAndRedirect(SceneType.VICTORY_SCENE);
        }
    }

    private void initializeMPLevel() {
        remotePlayer = new RemotePlayer(this);
        startSync();
    }

    public RemotePlayer getRemotePlayer() {
        return remotePlayer;
    }

    private void startSync() {
        sendThread = new Thread(() -> {
            try {
                while (true) {
                    HashMap<String, Long> myCommand = new HashMap<>();
                    myCommand.put("type", Network.CMD_POSITION);
                    myCommand.put("param", Double.valueOf(localPlayer.getPosition()).longValue());
                    StageManager.getInstance().getMpGameController().sendCommand(myCommand);

                    Thread.sleep(1000 / Network.TICK_RATE);
                }
            } catch (InterruptedException e) {
                // This is fine
            }
        });

        recvThread = new Thread(() -> {
            try {
                while (true) {
                    HashMap<String, Long> theirCommand = StageManager.getInstance()
                            .getMpGameController().receiveCommand();
                    if (theirCommand.get("type").equals(Network.CMD_POSITION)) {
                        Long param = theirCommand.get("param");
                        Platform.runLater(() -> {
                            remotePlayer.updatePosition(param.doubleValue());
                        });
                    } else if (theirCommand.get("type").equals(Network.CMD_DEATH)) {
                        Platform.runLater(() -> {
                            remotePlayer.stopAndUnmount();
                        });
                    }

                    Thread.sleep(1000 / Network.TICK_RATE);
                }
            } catch (InterruptedException e) {
                // This is fine
            }
        });

        sendThread.start();
        recvThread.start();
    }

    private void stopSync() {
        sendThread.interrupt();
        recvThread.interrupt();
    }

    public void onLocalPlayerDead() {
        if (!isMPLevel()) {
            gameOver();
        } else {
            HashMap<String, Long> myCommand = new HashMap<>();
            myCommand.put("type", Network.CMD_DEATH);
            myCommand.put("param", 0L);
            StageManager.getInstance().getMpGameController().sendCommand(myCommand);
            gameOver();
        }
    }
}
