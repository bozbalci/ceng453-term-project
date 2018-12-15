package com.twentythree.spaceclient.scene;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.controller.HttpBasicAuthContainer;
import com.twentythree.spaceclient.controller.StageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class LoginScene {
    private GridPane pane;
    private Scene scene;

    private LoginScene(GridPane pane, Scene scene) {
        this.pane = pane;
        this.scene = scene;
    }

    private static LoginScene instance;

    public static LoginScene create(StageManager stageManager) {
        GridPane pane = new GridPane();

        pane.setAlignment(Pos.CENTER);
        pane.setHgap(GUI.DEFAULT_GAP);
        pane.setVgap(GUI.DEFAULT_GAP);
        pane.setPadding(new Insets(GUI.DEFAULT_INSET, GUI.DEFAULT_INSET, GUI.DEFAULT_INSET, GUI.DEFAULT_INSET));

        Label usernameLabel = new Label("Username: ");
        pane.add(usernameLabel, 0, 1);

        TextField usernameField = new TextField();
        pane.add(usernameField, 1, 1);

        Label passwordLabel = new Label("Password: ");
        pane.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        pane.add(passwordField, 1, 2);

        Button login = new Button("Login");
        login.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (!(username.length() > 0 && password.length() > 0)) {
                return;
            }

            boolean loggedIn = stageManager.getRequestController().login(
                    new HttpBasicAuthContainer(username, password));

            if (loggedIn) {
                stageManager.toScene(SceneType.MAIN_MENU_SCENE);
            } else {
                // Display error message
                System.out.println("Could not log in!");
            }
        });

        Button register = new Button("Register");
        register.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            stageManager.getRequestController().register(new HttpBasicAuthContainer(username, password));
            stageManager.toScene(SceneType.MAIN_MENU_SCENE);
        });

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(login);
        hBox.getChildren().add(register);
        pane.add(hBox, 1, 4);

        stageManager.setTitle("Space Shooter: Login");
        instance = new LoginScene(pane, new Scene(pane, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT));
        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}