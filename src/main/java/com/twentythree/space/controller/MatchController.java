package com.twentythree.space.controller;

import com.twentythree.space.entity.Match;
import com.twentythree.space.entity.Player;
import com.twentythree.space.exception.MatchNotFoundException;
import com.twentythree.space.exception.PlayerNotFoundException;
import com.twentythree.space.repository.MatchRepository;
import com.twentythree.space.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MatchController {
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/match")
    Match createMatch(@RequestBody Match match) {
        return matchRepository.save(match);
    }

    @GetMapping("/match/{matchId}/{playerId}")
    Match getMatch(@PathVariable long matchId, @PathVariable long playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException((playerId)));

        return matchRepository.findByMatchIdAndPlayer(matchId, player)
                .orElseThrow(() -> new MatchNotFoundException((matchId)));
    }
}
