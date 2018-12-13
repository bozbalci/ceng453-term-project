package com.twentythree.space.controller;

import com.twentythree.space.entity.LiveMatchManagerSingleton;
import com.twentythree.space.entity.Match;
import com.twentythree.space.entity.MatchType;
import com.twentythree.space.entity.Player;
import com.twentythree.space.exception.ForbiddenException;
import com.twentythree.space.exception.MatchNotFoundException;
import com.twentythree.space.exception.PlayerNotFoundException;
import com.twentythree.space.repository.MatchRepository;
import com.twentythree.space.repository.PlayerRepository;
import com.twentythree.space.util.ScoreRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/")
    List<Match> getAllMatches() {return matchRepository.findAll();}

    @PostMapping("/")
    Match createMatch(@RequestBody Match match) {
        long playerId = match.getPlayer().getId();

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException((playerId)));

        match.setPlayer(player);

        return matchRepository.save(match);
    }

    @PostMapping("/submit-sp-score")
    Match submitSingleplayerScore(@RequestBody ScoreRequestWrapper req, Authentication auth) {
        if (auth == null) {
            throw new ForbiddenException();
        }

        final Player player = (Player) auth.getPrincipal();
        long score = req.getScore();
        long matchId = LiveMatchManagerSingleton.getInstance().acquireMatchId();

        Match match = new Match();
        match.setPlayer(player);
        match.setScore(score);
        match.setMatchId(matchId);
        match.setMatchType(MatchType.SINGLEPLAYER);

        return matchRepository.save(match);
    }

    @PostMapping("/submit-mp-score")
    Match submitMultiplayerScore(@RequestBody ScoreRequestWrapper req, Authentication auth) {
        if (auth == null) {
            throw new ForbiddenException();
        }

        final Player player = (Player) auth.getPrincipal();
        long score = req.getScore();

        return LiveMatchManagerSingleton.getInstance().submitScore(player.getId(), score);
    }

    @GetMapping("/{matchId}/{playerId}")
    Match getMatch(@PathVariable long matchId, @PathVariable long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException((playerId)));

        return matchRepository.findByMatchIdAndPlayer(matchId, player)
                .orElseThrow(() -> new MatchNotFoundException((matchId)));
    }

    @DeleteMapping("/{id}")
    void deleteMatch(@PathVariable long id) {
        matchRepository.deleteById(id);
    }

    public long getMaximumMatchId() {
        return matchRepository.getMaximumMatchId();
    }

    public Match save(Match match) {
        return matchRepository.save(match);
    }
}
