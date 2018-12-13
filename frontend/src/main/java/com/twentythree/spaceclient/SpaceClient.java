package com.twentythree.spaceclient;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SpaceClient extends Application {
    @Override
    public void start(Stage primaryStage) {
        Text text = new Text(40, 40, "Programming is fun!");
        Pane pane = new Pane(text);

        BorderPane borderPane = new BorderPane(pane);

        Scene scene = new Scene(borderPane, 400, 350);
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
