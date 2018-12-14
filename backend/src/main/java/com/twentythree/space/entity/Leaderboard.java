package com.twentythree.space.entity;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private List<LeaderboardEntry> entries;

    public Leaderboard(List<LeaderboardEntry> entries) {
        this.entries = entries;
    }
    public static Leaderboard fromNativeQueryList(List<Object[]> list) {
        List<LeaderboardEntry> entries = new ArrayList<>();

        for (Object[] entry : list) {
            entries.add(LeaderboardEntry.fromNativeQueryObject(entry));
        }

        return new Leaderboard(entries);
    }
}
