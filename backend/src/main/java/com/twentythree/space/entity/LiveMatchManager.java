package com.twentythree.space.entity;

import com.twentythree.space.exception.MatchNotFoundException;
import com.twentythree.space.repository.MatchRepository;
import com.twentythree.space.service.MatchmakingService;
import com.twentythree.space.util.BeanUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LiveMatchManager {
    private static LiveMatchManager instance = new LiveMatchManager();

    private MatchRepository matchRepository;

    private LiveMatchManager() {
        matches = new ArrayList<>();

        matchRepository = BeanUtil.getBean(MatchRepository.class);
        currentMatchId = matchRepository.getMaximumMatchId() + 1;
    }

    public static LiveMatchManager getInstance() {
        return instance;
    }

    private List<LiveMatch> matches;

    private long currentMatchId;

    public long createLiveMatch(long playerOneId, long playerTwoId) {
        long matchId = acquireMatchId();

        LiveMatch liveMatch = new LiveMatch(matchId, playerOneId, playerTwoId);
        matches.add(liveMatch);

        // These players found a match, so they should be removed from the MM queue
        MatchmakingService.getInstance().removePlayerById(playerOneId);
        MatchmakingService.getInstance().removePlayerById(playerTwoId);

        return liveMatch.getMatchId();
    }

    public Match submitScore(long playerId, long score) {
        Match result = null;
        Boolean found = false;
        Iterator<LiveMatch> it = matches.iterator();

        while (!found && it.hasNext()) {
            LiveMatch liveMatch = it.next();

            if (liveMatch.hasPlayer(playerId)) {
                found = true;
                result = liveMatch.submitScore(playerId, score);

                if (liveMatch.isFinished()) {
                    it.remove();
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
