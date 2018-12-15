package com.twentythree.spaceclient.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;

public class LevelProvider {
    private IntegerProperty currentLevel;
    private LongProperty totalScore;

    public void reset() {
        currentLevel = new SimpleIntegerProperty(1);
        totalScore = new SimpleLongProperty(0);
    }

    public void incrementLevel() {
        currentLevel.setValue(currentLevel.getValue() + 1);
    }

    public long getEnemyCountForLevel() {
        switch (currentLevel.getValue()) {
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return 100;
        }
    }

    public IntegerProperty getCurrentLevel() {
        return currentLevel;
    }

    public LongProperty getTotalScore() {
        return totalScore;
    }

    public void setScore(Long value) {
        totalScore.setValue(value);
    }
}
