package com.twentythree.space.entity;

import com.twentythree.space.controller.MatchController;
import com.twentythree.space.exception.MatchNotFoundException;
import com.twentythree.space.repository.MatchRepository;
import com.twentythree.space.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LiveMatchManagerSingleton {
    private static LiveMatchManagerSingleton instance = new LiveMatchManagerSingleton();

    private MatchRepository matchRepository;

    private LiveMatchManagerSingleton() {
        matches = new ArrayList<>();

        matchRepository = BeanUtil.getBean(MatchRepository.class);
        currentMatchId = matchRepository.getMaximumMatchId();
    }

    public static LiveMatchManagerSingleton getInstance() {
        return instance;
    }

    private List<LiveMatch> matches;

    private long currentMatchId;

    public long createLiveMatch(long playerOneId, long playerTwoId) {
        LiveMatch liveMatch = new LiveMatch(currentMatchId, playerOneId, playerTwoId);
        matches.add(liveMatch);

        currentMatchId++;

        return liveMatch.getMatchId();
    }

    public Match submitScore(long playerId, long score) {
        Match result = null;
        Boolean found = false;
        Iterator<LiveMatch> it = matches.iterator();

        while (!found || it.hasNext()) {
            LiveMatch liveMatch = it.next();

            if (liveMatch.hasPlayer(playerId)) {
                result = liveMatch.submitScore(playerId, score);

                if (liveMatch.isFinished()) {
                    it.remove();
                    found = true;
                }
            }
        }

        if (!found) {
            throw new MatchNotFoundException();
        }

        return result;
    }

    public long acquireMatchId() {
        long acquired = currentMatchId;
        currentMatchId++;
        return acquired;
    }
}
