package com.twentythree.spaceclient.scene;

import com.twentythree.spaceclient.constants.GUI;
import com.twentythree.spaceclient.constants.SceneType;
import com.twentythree.spaceclient.controller.LeaderboardEntryObject;
import com.twentythree.spaceclient.controller.LeaderboardObject;
import com.twentythree.spaceclient.controller.StageManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

import java.util.Iterator;

public class LeaderboardScene {
    private TabPane pane;
    private Scene scene;

    private LeaderboardScene(TabPane pane, Scene scene) {
        this.pane = pane;
        this.scene = scene;
    }

    private static LeaderboardScene instance;

    private static Tab createLeaderboardTab(StageManager stageManager, String title, boolean weekly) {
        Tab tab = new Tab();

        tab.setText(title);

        GridPane childPane = new GridPane();

        childPane.setAlignment(Pos.CENTER);
        childPane.setHgap(GUI.DEFAULT_GAP);
        childPane.setVgap(GUI.DEFAULT_GAP);
        childPane.setPadding(new Insets(GUI.DEFAULT_INSET, GUI.DEFAULT_INSET,
                GUI.DEFAULT_INSET, GUI.DEFAULT_INSET));

        LeaderboardObject leaderboard = stageManager.getRequestController().getLeaderboard(weekly);
        Iterator<LeaderboardEntryObject> it = leaderboard.getEntries().iterator();
        int rowIndex = 0;

        while (it.hasNext()) {
            LeaderboardEntryObject entry = it.next();

            Label usernameLabel = new Label(entry.getUsername());
            Label scoreLabel = new Label(((Long) entry.getScore()).toString());

            childPane.add(usernameLabel, 0, rowIndex);
            childPane.add(scoreLabel, 1, rowIndex);

            rowIndex++;
        }

        Button backToMainMenu = new Button("Back to Main Menu");
        backToMainMenu.setOnAction(e -> {
            stageManager.toScene(SceneType.MAIN_MENU_SCENE);
        });
        childPane.add(backToMainMenu, 1, rowIndex + 1);

        tab.setContent(childPane);
        return tab;
    }

    public static LeaderboardScene create(StageManager stageManager) {
        TabPane tabPane = new TabPane();

        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab allTimeTab = createLeaderboardTab(stageManager, "All Time", false);
        Tab weeklyTab = createLeaderboardTab(stageManager, "Weekly", true);

        tabPane.getTabs().add(allTimeTab);
        tabPane.getTabs().add(weeklyTab);

        instance = new LeaderboardScene(tabPane, new Scene(tabPane, GUI.WINDOW_WIDTH, GUI.WINDOW_HEIGHT));

        stageManager.setTitle("Space Shooter: Leaderboard");

        return instance;
    }

    public Scene getScene() {
        return scene;
    }
}
