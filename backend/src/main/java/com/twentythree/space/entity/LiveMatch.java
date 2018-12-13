package com.twentythree.space.entity;

import com.twentythree.space.exception.PlayerNotFoundException;
import com.twentythree.space.repository.MatchRepository;
import com.twentythree.space.repository.PlayerRepository;
import com.twentythree.space.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

public class LiveMatch {
    private PlayerRepository playerRepository;
    private MatchRepository matchRepository;

    private long playerOneId, playerTwoId, matchId;
    private Boolean playerOneSubmittedScore, playerTwoSubmittedScore;

    public Match submitScore(long playerId, long score) {
        Boolean isPlayerOne = playerId == playerOneId;

        if (isPlayerOne) {
            playerOneSubmittedScore = true;
        } else {
            playerTwoSubmittedScore = true;
        }

        Match match = new Match();
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException(playerId));
        match.setPlayer(player);
        match.setScore(score);
        match.setMatchId(matchId);
        match.setMatchType(MatchType.MULTIPLAYER);

        return matchRepository.save(match);
    }

    public Boolean hasPlayer(long playerId) {
        return playerId == playerOneId || playerId == playerTwoId;
    }

    public Boolean isFinished() {
        return playerOneSubmittedScore && playerTwoSubmittedScore;
    }

    public long getMatchId() {
        return matchId;
    }

    public LiveMatch(long matchId, long playerOneId, long playerTwoId) {
        this.matchId = matchId;
        this.playerOneId = playerOneId;
        this.playerTwoId = playerTwoId;
        this.playerOneSubmittedScore = false;
        this.playerTwoSubmittedScore = false;

        matchRepository = BeanUtil.getBean(MatchRepository.class);
        playerRepository = BeanUtil.getBean(PlayerRepository.class);
    }
}
