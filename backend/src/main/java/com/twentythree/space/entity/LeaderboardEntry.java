package com.twentythree.space.entity;

import java.math.BigDecimal;

public class LeaderboardEntry {
    private String username;
    private long score;

    public LeaderboardEntry(String username, long score) {
        this.username = username;
        this.score = score;
    }

    public static LeaderboardEntry fromNativeQueryObject(Object[] objects) {
        // this could better be handled using a Hibernate custom constructor
        // but I am not sufficiently skilled at Java to do that

        String username = (String) objects[0];
        long score = ((BigDecimal) objects[1]).longValue();

        return new LeaderboardEntry(username, score);
    }
}
