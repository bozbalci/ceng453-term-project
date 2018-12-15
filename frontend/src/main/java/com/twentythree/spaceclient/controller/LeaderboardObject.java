package com.twentythree.spaceclient.controller;

import java.util.List;

public class LeaderboardObject {
    private List<LeaderboardEntryObject> entries;

    public List<LeaderboardEntryObject> getEntries() {
        return entries;
    }

    public void setEntries(List<LeaderboardEntryObject> entries) {
        this.entries = entries;
    }
}
