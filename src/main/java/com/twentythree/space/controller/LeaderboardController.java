package com.twentythree.space.controller;

import com.twentythree.space.entity.Player;
import com.twentythree.space.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/weekly")
    List<Object[]> getSevenDaysLeaderboard() {
        return matchRepository.getSevenDaysLeaderboard(10);
    }

    @GetMapping("/all")
    List<Object[]> getAllTimeLeaderboard() {
        return matchRepository.getSevenDaysLeaderboard(10);
    }
}
