package com.twentythree.spaceclient.scene;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.stage.StageManager;
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
    private StageManager stageManager;
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

        Label username = new Label("Username: ");
        pane.add(username, 0, 1);

        TextField usernameField = new TextField();
        pane.add(usernameField, 1, 1);

        Label password = new Label("Password: ");
        pane.add(password, 0, 2);

        PasswordField passwordField = new PasswordField();
        pane.add(passwordField, 1, 2);

        Button login = new Button("Login");
        login.setOnAction(e -> {
            if (usernameField.getText().length() > 0 && passwordField.getText().length() > 0) {
                stageManager.toScene(SceneType.MAIN_MENU_SCENE);
            }
        });

        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.getChildren().add(login);
        pane.add(hBox, 1, 4);

        stageManager.setTitle("Space Shooter: Login");
        instance = new LoginScene(pane, new Scene(pane, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT));
        instance.stageManager = stageManager;
        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}