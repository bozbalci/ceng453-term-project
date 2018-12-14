package com.twentythree.spaceclient;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Shape;

import java.sql.Time;
import java.util.Random;
import java.util.Vector;

public class GameScene {
    Scene gameScene;
    Vector<Rectangle> enemyList = new Vector<Rectangle>();
    int Score = 0;

    public GameScene() {
        Pane pane = new Pane();

        Rectangle playerBox = new Rectangle(20, 20, 15 , 15);
        playerBox.setStroke(Color.BLACK);
        playerBox.setFill(Color.WHITE);
        pane.getChildren().add(playerBox);

        Timeline automatedAttack = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Circle projectile = new Circle(playerBox.getX(), playerBox.getY(), 2);
                projectile.setStroke(Color.BLACK);
                projectile.setFill(Color.BLUE);
                pane.getChildren().add(projectile);
                Timeline animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveProjectile(projectile)));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            }

            private void moveProjectile(Circle projectile) {
                projectile.setCenterY(projectile.getCenterY() - 5);
                Bounds proBounds = projectile.getBoundsInParent();
                if (!enemyList.isEmpty()) {
                    for (Rectangle re: enemyList) {
                        Bounds reBound = re.getBoundsInParent();
                        if (reBound.intersects(proBounds) == true) {
                            System.out.println("Hit");
                            Score += 50;
                            pane.getChildren().remove(re);
                        }
                    }
                }
            }
        }));

        automatedAttack.setCycleCount(Timeline.INDEFINITE);
        automatedAttack.play();

        Timeline enemySpawn = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Rectangle enemy = new Rectangle(new Random().nextInt(200), 20, 10, 10);
                enemy.setStroke(Color.BLACK);
                enemy.setFill(Color.BLACK);
                pane.getChildren().add(enemy);
                Timeline enemyAttackAnim = new Timeline(new KeyFrame(Duration.seconds(1.5), e -> enemyAttack(enemy)));
                enemyAttackAnim.setCycleCount(Timeline.INDEFINITE);
                enemyAttackAnim.play();
                enemyList.add(enemy);
            }

            private void enemyAttack(Rectangle enemy) {
                Circle projectile = new Circle(enemy.getX() + enemy.getWidth() / 2, enemy.getY() + enemy.getHeight() / 2, 2);
                projectile.setStroke(Color.BLACK);
                projectile.setFill(Color.RED);
                pane.getChildren().add(projectile);
                Timeline animation = new Timeline(new KeyFrame(Duration.millis(50), e -> moveProjectile(projectile)));
                animation.setCycleCount(Timeline.INDEFINITE);
                animation.play();
            }

            private void moveProjectile(Circle projectile) {
                projectile.setCenterY(projectile.getCenterY() + 5);
                if (projectile.getCenterY() > 350) {
                    pane.getChildren().remove(projectile);
                }
            }
        }));

        enemySpawn.setCycleCount(10);
        enemySpawn.play();

        gameScene = new Scene(pane, 200, 200);

        gameScene.setOnMouseMoved(e -> {
            playerBox.setX(e.getX() - playerBox.getWidth()/2);
            playerBox.setY(e.getY() - playerBox.getHeight()/2);
        });
    }

    public Scene ReturnGameScene() {

        return gameScene;
    }
}
